package com.fiit.g131.mafia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;

public class TurnActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<ArrayList<String>> roles;
    ArrayList<String> names, died;
    Vector<String> cur_names;
    int cur_role = 0, prev_cur_role;
    String[] role_name;
    ArrayAdapter<String> adapter;

    void nextturn(){
        String msg = "";
        for (String tmps: roles.get(cur_role)){
            msg += tmps;
            msg += '\n';
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
        builder.setTitle(role_name[cur_role]);
        builder.setMessage(msg);
        builder.setPositiveButton(getResources().getString(R.string.start_move), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cur_names.clear();
                for (int j = 0; j < 14; ++j){
                    if (j == cur_role) continue;
                    for (String tmps: roles.get(j)){
                        cur_names.add(tmps);
                    }
                }
                adapter.notifyDataSetChanged();
                prev_cur_role = cur_role;
                ++cur_role;
                if (cur_role == 14) cur_role = 0;
                for(; roles.get(cur_role).size() == 0 && cur_role < 14; ++cur_role);
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);

        lv = (ListView) findViewById(R.id.lv);

        role_name = getIntent().getStringArrayExtra("roles_names");
        names = getIntent().getStringArrayListExtra("names");

        roles = new ArrayList<>();
        cur_names = new Vector<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cur_names);
        lv.setAdapter(adapter);

        for (int i = 0; i < 14; i++) {
            roles.add(getIntent().getStringArrayListExtra(role_name[i]));
        }

        nextturn();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (prev_cur_role == 0 || prev_cur_role == 2 || prev_cur_role == 4){
                    died.add(cur_names.get(i));
                }
                nextturn();
            }
        });

    }

    @Override
    public void onBackPressed() {
    }

}

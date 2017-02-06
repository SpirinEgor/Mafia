package com.fiit.g131.mafia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    void nextturn(){  //создание alertdialog
        if (cur_role == 12 || cur_role == 13) {
            cur_role = 14;
        }
        String msg = "";
        if (cur_role != 14){
            if (cur_role == 0){
                for (String tmps: roles.get(1)){
                    msg += tmps;
                    msg += '\n';
                }
            }
            if (cur_role == 2){
                for (String tmps: roles.get(3)){
                    msg += tmps;
                    msg += '\n';
                }
            }
            for (String tmps: roles.get(cur_role)){
                msg += tmps;
                msg += '\n';
            }
        }
        String title = (cur_role == 14 ? getResources().getString(R.string.vote) : role_name[cur_role]);
        AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(getResources().getString(R.string.start_move), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cur_names.clear();
                if (cur_role != 8 && cur_role != 14){
                    for (int j = 0; j < 14; ++j){
                        if (j == cur_role) continue;
                        if (cur_role == 0 && j == 1) continue;
                        if (cur_role == 2 && j == 2) continue;
                        for (String tmps: roles.get(j)){
                            cur_names.add(tmps);
                        }
                    }
                }else if (cur_role != 14){
                    for (int j = 0; j < died.size(); ++j){
                        cur_names.add(died.get(j));
                    }
                }else{
                    for (int j = 0; j < 14; ++j){
                        for (String tmps: roles.get(j)){
                            cur_names.add(tmps);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                prev_cur_role = cur_role;
                ++cur_role;
                for (; cur_role < 14; ++cur_role){
                    if (roles.get(cur_role).size() != 0) break;
                }
                if (cur_role == 13 && roles.get(cur_role).size() == 0){
                    ++cur_role;
                }
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

        role_name = getIntent().getStringArrayExtra("roles_names");  //получаем список ролей
        names = getIntent().getStringArrayListExtra("names");  //получаем список игроков
        roles = new ArrayList<>();
        cur_names = new Vector<>();
        died = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cur_names);
        lv.setAdapter(adapter);

        for (int i = 0; i < 14; i++) {
            roles.add(getIntent().getStringArrayListExtra(role_name[i]));  //список игроков по ролям
        }

        for (; cur_role < 14; ++cur_role){
            if (roles.get(cur_role).size() != 0) break;
        }
        if (cur_role == 13 && roles.get(cur_role).size() == 0){
            ++cur_role;
        }
        nextturn(); //ход роли
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (prev_cur_role == 0 || prev_cur_role == 2 || prev_cur_role == 4) {
                    died.add(cur_names.get(i));
                }
                else if (prev_cur_role == 14){
                    died.add(cur_names.get(i));
                    for (int j = 0; j < died.size(); ++j){
                        for (int k = 0; k < roles.size(); ++k){
                            if (roles.get(k).contains(died.get(j))) roles.get(k).remove(died.get(j));
                        }
                        names.remove(died.get(j));
                    }
                    cur_role = 0;
                    for (; cur_role < 14; ++cur_role){
                        if (roles.get(cur_role).size() != 0) break;
                    }
                    if (cur_role == 13 && roles.get(cur_role).size() == 0){
                        ++cur_role;
                    }
                }
                nextturn();
            }
        });


    }

    @Override
    public void onBackPressed() {
    }

}

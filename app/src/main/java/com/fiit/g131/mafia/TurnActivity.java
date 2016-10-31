package com.fiit.g131.mafia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class TurnActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<HashSet<String>> roles;
    ArrayList<String> names, died;
    Vector<String> cur_names;
    int cur;
    String[] role_name;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);

        lv = (ListView) findViewById(R.id.lv);

        role_name = getIntent().getStringArrayExtra("roles_names");
        names = getIntent().getStringArrayListExtra("names");
        cur = getIntent().getExtras().getInt("cur");

        roles = new ArrayList<>();
        ArrayList<String> tmp = new ArrayList<>();
        HashSet<String> h = new HashSet<>();
        cur_names = new Vector<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cur_names);

        for (int i = 0; i < 14; i++) {
            tmp.clear();
            h.clear();
            tmp = getIntent().getStringArrayListExtra(role_name[i]);
            for (int q = 0; q < tmp.size(); q++) {
                h.add(tmp.get(q));
            }
            roles.add(h);
        }

        for (int i = 0; i < 1; ++i){
            h.clear();
            h = roles.get(i);
            final int cur_role = i;
            cur_names.clear();
            if (h.size() == 0) continue;
            AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
            builder.setTitle(role_name[i]);
            String cur_players = "";
            Iterator<String> it = h.iterator();
            for(; it.hasNext();){
                cur_players += it.next().toString() + "\n";
            }
            builder.setMessage(cur_players);
            builder.setPositiveButton(getResources().getString(R.string.start_move), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (int j = 0; j < names.size(); ++j)
                        cur_names.add(names.get(j));
                    do_the_role(cur_role);
                    adapter.notifyDataSetChanged();
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        for (int i=0; i<14;) {
            String cur_role = role_name[i];
            String cur_names = "";
            for (String s: roles.get(i)){
                cur_names+=s+" ";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
            builder.setTitle(cur_role).setMessage(cur_names).setCancelable(false).
                    setNegativeButton(getResources().getString(R.string.start_turn), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public void onBackPressed(){}

    void do_the_role(int i){
        HashSet<String> h = new HashSet<>();
        h = roles.get(i);
        String[] tmp = {};
        tmp = h.toArray(new String[h.size()]);
        switch (i){
            case 0:
                for (int j = 0; j < tmp.length; ++j){
                    for (int k = 0; k < cur_names.size(); ++k){
                        if (cur_names.get(k).equals(tmp[j])) cur_names.remove(k);
                    }
                }
        }
    }

}

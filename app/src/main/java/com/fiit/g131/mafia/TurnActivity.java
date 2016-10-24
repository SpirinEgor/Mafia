package com.fiit.g131.mafia;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class TurnActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<HashSet<String>> roles;
    ArrayList<String> names;
    int cur;
    String[] role_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);

        lv = (ListView) findViewById(R.id.lv);

        role_name = getIntent().getStringArrayExtra("roles_names");
        names = getIntent().getStringArrayListExtra("names");
        cur = getIntent().getExtras().getInt("cur");

        roles = new ArrayList<>();
        ArrayList<String> tmp;
        HashSet<String> h = new HashSet<>();

        for (int i = 0; i < 14; i++) {
            tmp = getIntent().getStringArrayListExtra(role_name[i]);
            for (int q = 0; q < tmp.size(); q++) {
                h.add(tmp.get(q));
            }
            roles.add(h);
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
}

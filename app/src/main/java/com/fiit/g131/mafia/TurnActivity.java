package com.fiit.g131.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class TurnActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<ArrayList<String> > roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);
        String buf = new String();
        String[] role_name = getIntent().getStringArrayExtra("roles_names");
        int[] role_c = getIntent().getIntArrayExtra("roles_c");
        int kol = getIntent().getExtras().getInt("kol");
        int cur = getIntent().getExtras().getInt("cur");
        ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        roles = new ArrayList<>();
        String key = "a";
        ArrayList<String> tmp;
        for (int i = 0; i < kol; i++) {
            tmp = getIntent().getStringArrayListExtra(key);
            roles.add(tmp);
            key += "a";
        }
        Vector<String> for_adapter = new Vector<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, for_adapter);
        boolean fl;
        for (int i = 0; i < names.size(); i++) {
            fl = true;
            for (int q = 0; q < roles.get(cur).size(); q++) {
                if (roles.get(cur).get(q).equals(names.get(i))) {
                    fl = false;
                }
            }
            fl = true;
            if (fl) {
                for_adapter.add(names.get(i));
            }
        }
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }
}

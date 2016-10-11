package com.fiit.g131.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class Turn extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);
        String buf = new String();
        String[] role_name = getIntent().getStringArrayExtra("roles_names");
        int[] role_c = getIntent().getIntArrayExtra("roles_c");
        ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        ArrayList<ArrayList<String> > roles = getIntent().getParcelableExtra("roles");
        int kol = getIntent().getExtras().getInt("kol");
        int cur = getIntent().getExtras().getInt("cur");
        Vector<String> for_adapter = new Vector<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, for_adapter);
        boolean fl;
        for (int i = 0; i < names.size(); i++) {
            fl = true;
            for (int q = 0; q < roles.get(i).size(); q++) {
                if (roles.get(i).get(q) == names.get(i)) {
                    fl = false;
                }
            }
            if (fl) {
                for_adapter.add(names.get(i));
            }
        }
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }
}

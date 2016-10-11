package com.fiit.g131.mafia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class ShuffleActivity extends Activity{

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle);

        lv = (ListView) findViewById(R.id.lv);

        ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        int[] role_c = getIntent().getIntArrayExtra("roles_c");
        int rand;
        String[] role_name = getIntent().getStringArrayExtra("roles_names");
        Random r = new Random();
        Map<String, Vector<String> > roles = new HashMap<String, Vector<String> >();
        Vector <String> tmp_roles = new Vector<String>();
        Vector <String> for_adapter = new Vector<String>();
        String buf = new String();
        for (int j = 0; j < 14; j++) {
            buf = role_name[j];
            buf += ": ";
            for (int i = 0; i < role_c[j]; ++i) {
                rand = r.nextInt(names.size());
                tmp_roles.add(names.get(rand));
                buf += (names.get(rand));
                if (i != (role_c[j] - 1)) buf += ", ";
                names.remove(rand);
            }
            roles.put(role_name[j], tmp_roles);
            tmp_roles.clear();
            if (role_c[j] != 0) for_adapter.add(buf);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, for_adapter);
        lv.setAdapter(adapter);
    }

}

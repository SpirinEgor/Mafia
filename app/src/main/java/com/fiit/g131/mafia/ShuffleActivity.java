package com.fiit.g131.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ShuffleActivity extends Activity{

    ListView lv;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle);

        lv = (ListView) findViewById(R.id.lv);

        final ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        final int[] role_c = getIntent().getIntArrayExtra("roles_c");
        int rand;
        start = (Button) findViewById(R.id.start_game);
        final String[] role_name = getIntent().getStringArrayExtra("roles_names");
        Random r = new Random();
        final ArrayList<ArrayList<String> > roles = new ArrayList<>();
        ArrayList <String> tmp_roles = new ArrayList<>();
        Vector <String> for_adapter = new Vector<String>();
        String buf = new String();
        ArrayList<String> tmp_names = names;
        for (int j = 0; j < 14; j++) {
            buf = role_name[j];
            buf += ": ";
            for (int i = 0; i < role_c[j]; ++i) {
                rand = r.nextInt(tmp_names.size());
                tmp_roles.add(tmp_names.get(rand));
                buf += (tmp_names.get(rand));
                if (i != (role_c[j] - 1)) buf += ", ";
                tmp_names.remove(rand);
            }

            tmp_roles.clear();
            if (role_c[j] != 0) {
                for_adapter.add(buf);
                roles.add(tmp_roles);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, for_adapter);
        lv.setAdapter(adapter);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShuffleActivity.this, RolesActivity.class);
                intent.putExtra("names", names);
                intent.putExtra("roles_names", role_name);
                intent.putExtra("roles_c", role_c);
                String key;
                key = "a";
                for (int i = 0; i < roles.size(); i++) {
                    intent.putExtra(key, roles.get(i));
                    key += "a";
                }
                intent.putExtra("cur", 0);
                intent.putExtra("kol", roles.size());
                startActivity(intent);
            }
        });
    }

}

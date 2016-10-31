package com.fiit.g131.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ShuffleActivity extends Activity{

    ListView lv;
    ArrayList<String> names, tmp_names;
    Button start;
    Intent intent;
    ArrayList<ArrayList<String> > roles;
    int[] role_c;
    String[] role_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle);

        lv = (ListView) findViewById(R.id.lv);
        start = (Button) findViewById(R.id.start_game);

        names = getIntent().getStringArrayListExtra("names");
        role_c = getIntent().getIntArrayExtra("roles_c");
        role_name = getIntent().getStringArrayExtra("roles_names");

        int rand;
        Random r = new Random();

        roles = new ArrayList<>();
        Vector <String> for_adapter = new Vector<String>();

        tmp_names = new ArrayList<>();
        for (int i=0; i<names.size(); ++i)
            tmp_names.add(names.get(i));

        String buf = new String();
        for (int j = 0; j < 14; j++) {
            ArrayList <String> tmp_roles = new ArrayList<>();
            if (role_c[j] == 0) {
                roles.add(tmp_roles);
                continue;
            }
            buf = role_name[j];
            buf += ": ";
            for (int i = 0; i < role_c[j]; ++i) {
                rand = r.nextInt(tmp_names.size());
                tmp_roles.add(tmp_names.get(rand));
                buf += (tmp_names.get(rand));
                if (i != (role_c[j] - 1)) buf += ", ";
                tmp_names.remove(rand);
            }
            for_adapter.add(buf);
            roles.add(tmp_roles);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, for_adapter);
        lv.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ShuffleActivity.this, TurnActivity.class);
                intent.putExtra("names", names);
                intent.putExtra("roles_names", role_name);
                for (int i = 0; i < 14; i++) {
                    intent.putExtra(role_name[i], roles.get(i));
                }
                intent.putExtra("cur", 0);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){}

}

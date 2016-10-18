package com.fiit.g131.mafia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Set;

public class TurnActivity extends AppCompatActivity {

    Button cur_role;
    ArrayList<Set<String>> roles;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);
        final String[] role_name = getIntent().getStringArrayExtra("roles_names");
        final int[] role_c = getIntent().getIntArrayExtra("roles_c");
        final ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        roles = new ArrayList<>();
        final int kol = getIntent().getExtras().getInt("kol");
        final int cur = getIntent().getExtras().getInt("cur");
        cur_role = (Button) findViewById(R.id.cur_role);
        cur_role.setText(role_name[cur]);
        String key = "a";
        ArrayList<String> tmp;
        for (int i = 0; i < kol; i++) {
            tmp = getIntent().getStringArrayListExtra(key);
            for (int q = 0; q < tmp.size(); q++) {
                roles.get(i).add(tmp[q]);
            }
            key += "a";
        }

    }
}

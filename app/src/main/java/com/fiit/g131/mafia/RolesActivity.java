package com.fiit.g131.mafia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RolesActivity extends AppCompatActivity {

    Button cur_role;
    ArrayList<ArrayList<String>> roles;
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
        final Intent intent = new Intent(RolesActivity.this, TurnActivity.class);
        cur_role.setText(role_name[cur]);
        String key = "a";
        ArrayList<String> tmp;
        for (int i = 0; i < kol; i++) {
            tmp = getIntent().getStringArrayListExtra(key);
            roles.add(tmp);
            key += "a";
        }
        cur_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("names", names);
                intent.putExtra("roles_names", role_name);
                intent.putExtra("roles_c", role_c);
                String key;
                key = "a";
                for (int i = 0; i < roles.size(); i++) {
                    intent.putExtra(key, roles.get(i));
                }
                intent.putExtra("cur", cur);
                intent.putExtra("kol", roles.size());
                startActivity(intent);
            }
        });
    }
}

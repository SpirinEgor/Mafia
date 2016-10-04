package com.fiit.g131.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class StartActivity extends Activity {

    ListView lv_player;
    EditText add_edt;
    Button add_btn, next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        add_btn = (Button) findViewById(R.id.add_btn);
        add_edt = (EditText) findViewById(R.id.add_edt);
        lv_player = (ListView) findViewById(R.id.lv);
        next_btn = (Button) findViewById(R.id.start_next);

        final Vector <String> players = new Vector();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_player = add_edt.getText().toString();
                if (new_player.equals("")){
                    String noname=getResources().getString(R.string.no_name);
                    Toast toast = Toast.makeText(getApplicationContext(), noname, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    boolean b=true;
                    for (int i=0; i<players.size(); ++i)
                        if (players.elementAt(i).equals(new_player)) b=false;
                    if (b) {
                        players.add(new_player);
                        setAdapter(players);
                    }else{
                        String samename=getResources().getString(R.string.same_name);
                        Toast toast = Toast.makeText(getApplicationContext(), samename, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
                add_edt.setText("");
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (players.size()<4){
                    String few_players=getResources().getString(R.string.few_players);
                    Toast toast = Toast.makeText(getApplicationContext(), few_players, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    Intent intent = new Intent(StartActivity.this, PlayersActivity.class);
                    String[] names = new String[players.size()];
                    for (int i=0; i<players.size(); ++i)
                        names[i]=players.elementAt(i);
                    intent.putExtra("names", names);
                    startActivity(intent);
                }
            }
        });

    }

    void setAdapter(Vector players){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, players);
        lv_player.setAdapter(adapter);
    }

}

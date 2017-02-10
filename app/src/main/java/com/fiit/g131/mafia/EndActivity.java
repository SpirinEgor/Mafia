package com.fiit.g131.mafia;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EndActivity extends AppCompatActivity{

    int win; //кто победил
    ArrayList<String> names; //список игроков

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        win = getIntent().getIntExtra("win", 0); //получаем кто победил
        names = getIntent().getStringArrayListExtra("names");

        TextView winner = (TextView) findViewById(R.id.winner);
        Button back_to_menu = (Button) findViewById(R.id.back_to_menu);
        Button repied_game = (Button) findViewById(R.id.repied_game);

        if (win == 0){
            winner.setText(getResources().getString(R.string.red_win));
        }
        else{
            winner.setText(getResources().getString(R.string.black_win));
        }

        back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        repied_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this, StartActivity.class);
                intent.putStringArrayListExtra("names", names);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}

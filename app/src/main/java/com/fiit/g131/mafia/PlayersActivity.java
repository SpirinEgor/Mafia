package com.fiit.g131.mafia;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class PlayersActivity extends Activity{

    TextView hint;
    Button next;
    String[] role_name = new String[14];
    ArrayList<String> names = new ArrayList<>();
    EditText[] role_ind = new EditText[14];
    int[] role_c = new int[14];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        names = getIntent().getStringArrayListExtra("names");
        hint = (TextView) findViewById(R.id.hint);
        hint.setText("Рекомендуемый состав:\n"+names.size()/3+" мафий, из них 1 дон\n"+"1 комиссар\n"+(names.size()-names.size()/3-1)+" мирных жителей");

        next = (Button) findViewById(R.id.player_next);

        role_ind[0] = (EditText) findViewById(R.id.num_mafia);
        role_ind[1] = (EditText) findViewById(R.id.num_don);
        role_ind[2] = (EditText) findViewById(R.id.num_yakudza);
        role_ind[3] = (EditText) findViewById(R.id.num_sensey);
        role_ind[4] = (EditText) findViewById(R.id.num_killer);
        role_ind[5] = (EditText) findViewById(R.id.num_werewolf);
        role_ind[6] = (EditText) findViewById(R.id.num_sherif);
        role_ind[7] = (EditText) findViewById(R.id.num_doctor);
        role_ind[8] = (EditText) findViewById(R.id.num_reviver);
        role_ind[9] = (EditText) findViewById(R.id.num_journalist);
        role_ind[10] = (EditText) findViewById(R.id.num_priest);
        role_ind[11] = (EditText) findViewById(R.id.num_lover);
        role_ind[12] = (EditText) findViewById(R.id.num_immortal);
        role_ind[13] = (EditText) findViewById(R.id.num_civilian);
        role_name[0] = getResources().getString(R.string.mafia);
        role_name[1] = getResources().getString(R.string.don);
        role_name[2] = getResources().getString(R.string.yakudza);
        role_name[3] = getResources().getString(R.string.sensey);
        role_name[4] = getResources().getString(R.string.killer);
        role_name[5] = getResources().getString(R.string.werewolf);
        role_name[6] = getResources().getString(R.string.sheriff);
        role_name[7] = getResources().getString(R.string.doctor);
        role_name[8] = getResources().getString(R.string.reviver);
        role_name[9] = getResources().getString(R.string.journalist);
        role_name[10] = getResources().getString(R.string.priest);
        role_name[11] = getResources().getString(R.string.lover);
        role_name[12] = getResources().getString(R.string.immortal);
        role_name[13] = getResources().getString(R.string.civilian);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                for (int i = 0; i < 14; i++) {
                    String role_count=role_ind[i].getText().toString();
                    try{
                        role_c[i] = Integer.parseInt(role_count);
                    }catch (Exception e){
                        role_c[i]=0;
                    }
                    sum += role_c[i];
                }
                if (sum!=names.size()){
                    String wrong_number=getResources().getString(R.string.wrong_number);
                    Toast toast = Toast.makeText(getApplicationContext(), wrong_number, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    Intent intent = new Intent(PlayersActivity.this, ShuffleActivity.class);
                    intent.putExtra("names", names);
                    intent.putExtra("roles_names", role_name);
                    intent.putExtra("roles_c", role_c);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onBackPressed(){}

}

package com.fiit.g131.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class PlayersActivity extends Activity{

    TextView hint;
    Button next;
    EditText mafia, don, civilian, sheriff, yakudza, sensey, killer, doctor, reviver, lover, prist, journalist, undiyng, lycanthrope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        final Vector <String> names = new Vector();
        String[] tmp = getIntent().getStringArrayExtra("names");
        for (int i=0; i<tmp.length; ++i)
            names.add(tmp[i]);

        hint = (TextView) findViewById(R.id.hint);
        hint.setText("Рекомендуемый состав:\n"+names.size()/3+" мафий, из них 1 дон\n"+"1 комиссар\n"+(names.size()-names.size()/3-1)+" мирных жителей");

        next = (Button) findViewById(R.id.player_next);

        mafia = (EditText) findViewById(R.id.num_mafia);
        don = (EditText) findViewById(R.id.num_don);
        civilian = (EditText) findViewById(R.id.num_civilian);
        sheriff = (EditText) findViewById(R.id.num_sherif);
        yakudza = (EditText) findViewById(R.id.num_yakudza);
        sensey = (EditText) findViewById(R.id.num_sensey);
        killer = (EditText) findViewById(R.id.num_killer);
        doctor = (EditText) findViewById(R.id.num_doctor);
        reviver = (EditText) findViewById(R.id.num_reviver);
        lover = (EditText) findViewById(R.id.num_lover);
        undiyng = (EditText) findViewById(R.id.num_undying);
        prist = (EditText) findViewById(R.id.num_prist);
        journalist = (EditText) findViewById(R.id.num_journalist);
        lycanthrope = (EditText) findViewById(R.id.num_lycanthrope);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c_mafia=Integer.parseInt(mafia.getText().toString());
                int c_don=Integer.parseInt(don.getText().toString());
                int c_sheriff=Integer.parseInt(sheriff.getText().toString());
                int c_civilian=Integer.parseInt(civilian.getText().toString());
                int c_yakudza=Integer.parseInt(yakudza.getText().toString());
                int c_sensey=Integer.parseInt(sensey.getText().toString());
                int c_doctor=Integer.parseInt(doctor.getText().toString());
                int c_reviver=Integer.parseInt(reviver.getText().toString());
                int c_undying=Integer.parseInt(undiyng.getText().toString());
                int c_prist=Integer.parseInt(prist.getText().toString());
                int c_journalist=Integer.parseInt(journalist.getText().toString());
                int c_lycanthrope=Integer.parseInt(lycanthrope.getText().toString());
                int c_lover=Integer.parseInt(lover.getText().toString());
                int c_killer=Integer.parseInt(killer.getText().toString());

                int sum=c_civilian+c_doctor+c_don+c_journalist+c_killer+c_lover+c_lycanthrope+c_mafia+c_prist+c_reviver+c_sensey+c_sheriff+c_undying+c_yakudza;
                if (sum!=names.size()){
                    String wrong_number=getResources().getString(R.string.wrong_number);
                    Toast toast = Toast.makeText(getApplicationContext(), wrong_number, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    Intent intent = new Intent(PlayersActivity.this, ShuffleActivity.class);
                    String[] tmp = new String[names.size()];
                    for (int i=0; i<names.size(); ++i)
                        tmp[i]=names.elementAt(i);
                    intent.putExtra("names", tmp);
                    intent.putExtra("mafia", c_mafia);
                    intent.putExtra("don", c_don);
                    intent.putExtra("sheriff", c_sheriff);
                    intent.putExtra("civilian", c_civilian);
                    intent.putExtra("yakudza", c_yakudza);
                    intent.putExtra("sensey", c_sensey);
                    intent.putExtra("killer", c_killer);
                    intent.putExtra("doctor", c_doctor);
                    intent.putExtra("reviver", c_reviver);
                    intent.putExtra("lover", c_lover);
                    intent.putExtra("lycanthrope", c_lycanthrope);
                    intent.putExtra("prist", c_prist);
                    intent.putExtra("journalist", c_journalist);
                    intent.putExtra("undying", c_undying);
                    startActivity(intent);
                }

            }
        });
    }

}

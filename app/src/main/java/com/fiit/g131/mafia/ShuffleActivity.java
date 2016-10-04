package com.fiit.g131.mafia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        final Vector<String> names = new Vector();
        String[] tmp = getIntent().getStringArrayExtra("names");
        for (int i=0; i<tmp.length; ++i)
            names.add(tmp[i]);
        int mafia = getIntent().getIntExtra("mafia", 0);
        int don = getIntent().getIntExtra("don", 0);
        int sheriff = getIntent().getIntExtra("sheriff", 0);
        int civilian = getIntent().getIntExtra("civilian", 0);
        int yakudza = getIntent().getIntExtra("yakudza", 0);
        int sensey = getIntent().getIntExtra("sensey", 0);
        int lover = getIntent().getIntExtra("lover", 0);
        int prist = getIntent().getIntExtra("prist", 0);
        int journalist = getIntent().getIntExtra("journalist", 0);
        int lycanthrope = getIntent().getIntExtra("lycanthrope", 0);
        int doctor = getIntent().getIntExtra("doctor", 0);
        int reviver = getIntent().getIntExtra("reviver", 0);
        int killer = getIntent().getIntExtra("killer", 0);
        int undying = getIntent().getIntExtra("undying", 0);

        int rand;
        Random r = new Random();
        Map<String, Vector<String> > roles = new HashMap<String, Vector<String> >();
        Vector <String> tmp_roles = new Vector<String>();
        Vector <String> for_adapter = new Vector<String>();
        String buf = new String();

        buf=getResources().getString(R.string.mafia);
        buf+=": ";
        for (int i=0; i<mafia; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(mafia-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("mafia", tmp_roles);
        tmp_roles.clear();
        if (mafia!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.don);
        buf+=": ";
        for (int i=0; i<don; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(don-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("don", tmp_roles);
        tmp_roles.clear();
        if (don!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.yakudza);
        buf+=": ";
        for (int i=0; i<yakudza; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(yakudza-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("yakudza", tmp_roles);
        tmp_roles.clear();
        if (yakudza!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.sensey);
        buf+=": ";
        for (int i=0; i<sensey; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(sensey-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("sensey", tmp_roles);
        tmp_roles.clear();
        if (sensey!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.killer);
        buf+=": ";
        for (int i=0; i<killer; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(killer-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("killer", tmp_roles);
        tmp_roles.clear();
        if (killer!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.sheriff);
        buf+=": ";
        for (int i=0; i<sheriff; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(sheriff-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("sheriff", tmp_roles);
        tmp_roles.clear();
        if (sheriff!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.doctor);
        buf+=": ";
        for (int i=0; i<doctor; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(doctor-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("doctor", tmp_roles);
        tmp_roles.clear();
        if (doctor!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.reviver);
        buf+=": ";
        for (int i=0; i<reviver; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(reviver-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("reviver", tmp_roles);
        tmp_roles.clear();
        if (reviver!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.lover);
        buf+=": ";
        for (int i=0; i<lover; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(lover-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("lover", tmp_roles);
        tmp_roles.clear();
        if (lover!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.prist);
        buf+=": ";
        for (int i=0; i<prist; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(prist-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("prist", tmp_roles);
        tmp_roles.clear();
        if (prist!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.journalist);
        buf+=": ";
        for (int i=0; i<journalist; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(journalist-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("journalist", tmp_roles);
        tmp_roles.clear();
        if (journalist!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.lycanthrope);
        buf+=": ";
        for (int i=0; i<lycanthrope; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(lycanthrope-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("lycanthrope", tmp_roles);
        tmp_roles.clear();
        if (lycanthrope!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.undying);
        buf+=": ";
        for (int i=0; i<undying; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(undying-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("undying", tmp_roles);
        tmp_roles.clear();
        if (undying!=0) for_adapter.add(buf);
        buf=getResources().getString(R.string.civilian);
        buf+=": ";
        for (int i=0; i<civilian; ++i){
            rand=r.nextInt(names.size());
            tmp_roles.add(names.elementAt(rand));
            buf+=(names.elementAt(rand));
            if (i!=(civilian-1)) buf+=", ";
            names.remove(rand);
        }
        roles.put("civilian", tmp_roles);
        tmp_roles.clear();
        if (civilian !=0) for_adapter.add(buf);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, for_adapter);
        lv.setAdapter(adapter);
    }

}

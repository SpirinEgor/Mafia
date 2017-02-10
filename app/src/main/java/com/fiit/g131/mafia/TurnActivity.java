package com.fiit.g131.mafia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class TurnActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<ArrayList<String>> roles;
    ArrayList<String> names, died;
    Vector<String> cur_names;
    int cur_role = 0, prev_cur_role;
    String[] role_name;
    ArrayAdapter<String> adapter;
    String silent = "", last_doc = "", last_lover = "";
    boolean doc = false; //лечил ли доктор себя
    boolean lov = false; //любила ли любовница себя

    void nextturn(){  //создание alertdialog
        if (cur_role == 12 || cur_role == 13) {  //если ход бессмертного или мирного, то переход к голосованию
            cur_role = 14;
        }
        if (cur_role == 5){ //проверка хода оборотня
            if (roles.get(4).size() > 0){
                ++cur_role;
                for (; cur_role < 14; ++cur_role){
                    if (roles.get(cur_role).size() != 0) break;
                }
                if (cur_role == 13 && roles.get(cur_role).size() == 0){
                    ++cur_role;
                }
            }
        }
        String msg = "";
        if (cur_role != 14){  //список игроков хода
            if (cur_role == 0){
                for (String tmps: roles.get(1)){
                    msg += tmps;
                    msg += '\n';
                }
            }
            if (cur_role == 2){
                for (String tmps: roles.get(3)){
                    msg += tmps;
                    msg += '\n';
                }
            }
            for (String tmps: roles.get(cur_role)){
                msg += tmps;
                msg += '\n';
            }
        }
        else{  //итоги ночи, кто убит и кто молчит
            if (died.size() > 0) {
                msg += getResources().getString(R.string.killed) + " ";
                for (int i = 0; i < died.size(); ++i) {
                    msg += died.get(i);
                    if (i != died.size() - 1) msg += ", ";
                }
                msg += "\n";
            }
            else{
                msg += getResources().getString(R.string.non_killed) + "\n";
            }
            if (silent != "" ){
                msg += "\n" + getResources().getString(R.string.silent) + " " + silent;
            }
            else{
                msg += getResources().getString(R.string.non_silent);
            }
        }
        String title = (cur_role == 14 ? getResources().getString(R.string.vote) : role_name[cur_role]);
        AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.start_move), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cur_names.clear();  //создание списка для Listview в зависимости от хода
                if (cur_role != 8 && cur_role != 14 && cur_role != 7){  //список всех игроков, кроме текущей роли, кроме Реаниматора, Доктора и Голосования
                    for (int j = 0; j < 14; ++j){
                        if (j == cur_role) continue;
                        if (cur_role == 0 && j == 1) continue;
                        if (cur_role == 2 && j == 2) continue;
                        for (String tmps: roles.get(j)){
                            cur_names.add(tmps);
                        }
                    }
                }else if (cur_role != 14 && cur_role != 7){  //список всех убитых игроков для реаниматора
                    for (int j = 0; j < died.size(); ++j){
                        cur_names.add(died.get(j));
                    }
                }
                else if (cur_role != 14){  //список всех игроков для доктора
                    for (int j = 0; j < 14; ++j){
                        for (String tmps: roles.get(j)){
                            cur_names.add(tmps);
                        }
                    }
                }
                else{  //список всех игроков для голосования
                    for (int j = 0; j < 14; ++j){
                        for (String tmps: roles.get(j)){
                            if (!died.contains(tmps)) cur_names.add(tmps);
                        }
                    }
                }
                cur_names.add(getResources().getString(R.string.miss));  //возможность пропуска хода
                adapter.notifyDataSetChanged();
                prev_cur_role = cur_role;
                ++cur_role;  //переход к следующей роли
                for (; cur_role < 14; ++cur_role){
                    if (roles.get(cur_role).size() != 0) break;
                }
                if (cur_role == 13 && roles.get(cur_role).size() == 0){
                    ++cur_role;
                }
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }  //ход роли, создание списка, AlertDialog

    void check_end_game(){
        int black = 0, red = 0;
        for (int i = 0; i < 14; ++i){
            for (String tmps: roles.get(i)){
                if (i < 5) ++black;
                else if (i == 5){
                    if (roles.get(4).size() == 0) ++black;
                    else ++red;
                }
                else ++red;
            }
        }
        if (red == 0){
            Intent intent = new Intent(TurnActivity.this, EndActivity.class);
            intent.putExtra("win", 1); //победа черных
            intent.putStringArrayListExtra("names", names);
            startActivity(intent);
        }
        else if (black == 0){
            Intent intent = new Intent(TurnActivity.this, EndActivity.class);
            intent.putExtra("win", 0); //победа красных
            intent.putStringArrayListExtra("names", names);
            startActivity(intent);
        }

    }  //проверка на конец игры

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);

        lv = (ListView) findViewById(R.id.lv);

        role_name = getIntent().getStringArrayExtra("roles_names");  //получаем список ролей
        names = getIntent().getStringArrayListExtra("names");  //получаем список игроков
        roles = new ArrayList<>();
        cur_names = new Vector<>();  //список для адаптера
        died = new ArrayList<>();  //список убитых
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cur_names);
        lv.setAdapter(adapter);

        for (int i = 0; i < 14; i++) {
            roles.add(getIntent().getStringArrayListExtra(role_name[i]));  //список игроков по ролям
        }

        for (; cur_role < 14; ++cur_role){
            if (roles.get(cur_role).size() != 0) break;
        }
        if (cur_role == 13 && roles.get(cur_role).size() == 0){
            ++cur_role;
        }
        nextturn(); //ход роли
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (prev_cur_role == 0 || prev_cur_role == 2 || prev_cur_role == 4 || prev_cur_role == 5) { //ход Мафии, Якудзы, Киллера и Оборотня
                    if (i < cur_names.size() - 1){
                        if (!roles.get(12).contains(cur_names.get((i)))) { //не убили ли бессмертного
                            died.add(cur_names.get(i));
                        }
                    }
                }
                else if (prev_cur_role == 1 || prev_cur_role == 3){  //Ход Дона и Сенсея
                    if (i < cur_names.size() - 1){
                        boolean guess = roles.get(6).contains(cur_names.get(i));
                        AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
                        builder.setTitle(getResources().getString(R.string.guess_sheriff));
                        builder.setCancelable(false);
                        builder.setMessage(guess ? "accepted" : "wrong");
                        builder.setPositiveButton(getResources().getString(R.string.next), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else if (prev_cur_role == 6){ //Ход Комиссара
                    if (i < cur_names.size() - 1){
                        boolean guess = roles.get(0).contains(cur_names.get(i)) ||
                                roles.get(1).contains(cur_names.get(i)) ||
                                roles.get(2).contains(cur_names.get(i)) ||
                                roles.get(3).contains(cur_names.get(i)) ||
                                roles.get(4).contains(cur_names.get(i));
                        if (roles.get(5).contains(cur_names.get(i))){
                            if (roles.get(4).size() > 0) guess = false;
                            else guess = true;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
                        builder.setTitle(getResources().getString(R.string.guess_black));
                        builder.setCancelable(false);
                        builder.setMessage(guess ? "accepted" : "wrong");
                        builder.setPositiveButton(getResources().getString(R.string.next), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else if (prev_cur_role == 7){ //ход Доктора
                    if (i < cur_names.size() - 1){
                        if (cur_names.get(i) != roles.get(7).get(0)){
                            if (last_doc != cur_names.get(i)) {
                                if (died.contains(cur_names.get(i))) {
                                    died.remove(cur_names.get(i));
                                }
                                last_doc = cur_names.get(i);
                            }
                            else{
                                Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.doctor_wrong), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                cur_role = 7;
                            }
                        }else{
                            if (!doc) {
                                doc = true;
                                if (died.contains(cur_names.get(i))) {
                                    died.remove(cur_names.get(i));
                                }
                                last_doc = cur_names.get(i);
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.doctor_repied), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                cur_role = 7;
                            }
                        }
                    }
                }
                else if (prev_cur_role == 8){ //ход Реаниматора
                    if (i < cur_names.size() - 1){
                        died.remove(cur_names.get(i));
                    }
                }
                else if (prev_cur_role == 9){ //ход Журналиста
                    if (i < cur_names.size() - 1){

                    }
                }
                else if (prev_cur_role == 10){ //ход Священника
                    if (i < cur_names.size() - 1){
                        Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.wake_up) + " " + cur_names.get(i), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        boolean guess = roles.get(0).contains(cur_names.get(i)) ||
                                roles.get(1).contains(cur_names.get(i)) ||
                                roles.get(2).contains(cur_names.get(i)) ||
                                roles.get(3).contains(cur_names.get(i)) ||
                                roles.get(4).contains(cur_names.get(i));
                        if (roles.get(5).contains(cur_names.get(i))){
                            if (roles.get(4).size() > 0) guess = false;
                            else guess = true;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(TurnActivity.this);
                        builder.setTitle(getResources().getString(R.string.guess_black));
                        builder.setCancelable(false);
                        builder.setMessage(guess ? "accepted" : "wrong");
                        builder.setPositiveButton(getResources().getString(R.string.next), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else if (prev_cur_role == 11){ //Ход Любовницы
                    if (i < cur_names.size() - 1){
                        if (cur_names.get(i) != roles.get(11).get(0)){
                            if (last_lover != cur_names.get(i)) {
                                silent = cur_names.get(i);
                                last_lover = cur_names.get(i);
                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.lover_wrong), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                cur_role = 11;
                            }
                        }else{
                            if (!lov) {
                                lov = true;
                                silent = cur_names.get(i);
                                last_lover = cur_names.get(i);
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.lover_repied), Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                cur_role = 11;
                            }
                        }
                    }
                }
                else if (prev_cur_role == 14){  //Голосование
                    if (i < cur_names.size() - 1){
                        died.add(cur_names.get(i));
                    }
                    for (int j = 0; j < died.size(); ++j){
                        for (int k = 0; k < roles.size(); ++k){
                            if (roles.get(k).contains(died.get(j))) roles.get(k).remove(died.get(j));
                        }
                    }
                    cur_role = 0;
                    for (; cur_role < 14; ++cur_role){
                        if (roles.get(cur_role).size() != 0) break;
                    }
                    if (cur_role == 13 && roles.get(cur_role).size() == 0){
                        ++cur_role;
                    }
                    died.clear();
                    silent = "";
                }
                check_end_game();
                nextturn();
            }
        });


    }

    @Override
    public void onBackPressed() {
    }

}

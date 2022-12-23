package com.example.databaselab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add).setOnClickListener(x -> {
            Intent goForward = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(goForward);
        });
        findViewById(R.id.arena).setOnClickListener(x -> {
            Intent goForward = new Intent(MainActivity.this, ArenaActivity.class);
            startActivity(goForward);
        });

        findViewById(R.id.find).setOnClickListener(x -> {
            Intent goForward = new Intent(MainActivity.this, mainFindActivity.class);
            startActivity(goForward);
        });

        Button buttonLoadClasses = findViewById(R.id.addClasses);
        buttonLoadClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String[][] ClassesArr = {
                        {"mage", "2", "4", "25"},
                        {"warrior", "1.1", "2", "100"},
                        {"archer", "1.5", "3", "55"}
                };
                for (int i = 0; i < ClassesArr.length; i++) {
                    ClassDescription classToInsert = new ClassDescription();
                    classToInsert.setClassName(ClassesArr[i][0]);
                    classToInsert.setMinDamageMultiplier(Float.parseFloat(ClassesArr[i][1]));
                    classToInsert.setMaxDamageMultiplier(Float.parseFloat(ClassesArr[i][2]));
                    classToInsert.setHealthBonus(Float.parseFloat(ClassesArr[i][3]));
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().ClassesDao().insert(classToInsert);
                }
                Toast.makeText(getApplicationContext(), "База классов загружена", Toast.LENGTH_LONG).show();
            }
        });
        getPersons();
    }

    private void getPersons() {
        class GetPersons extends AsyncTask<Void, Void, List<Player>> {

            @Override
            protected List<Player> doInBackground(Void... voids) {
                List<Player> playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getAll();
                return playerList;
            }

            @Override
            protected void onPostExecute(List<Player> player) {
                super.onPostExecute(player);
                PlayerAdapter adapter = new PlayerAdapter(MainActivity.this, player);
                recyclerView.setAdapter(adapter);
            }
        }

        GetPersons gt = new GetPersons();
        gt.execute();
    }

    /*private void importClasses(){

        class SetClasses extends AsyncTask<Void, Void, Integer> {

            @Override
            protected Integer doInBackground(Void... voids) {
                Integer classCount = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().ClassesDao().getClassNum();
                return classCount;
            }

            @Override
            protected void onPostExecute(Integer num) {
                super.onPostExecute(num);
                if (num == 0){
                    Log.d("Ya","Num is zero");
                    String[][] ClassesArr = {
                            {"mage", "2", "4", "25"},
                            {"warrior", "1.1", "2","100"},
                            {"archer", "1.5", "3","55"}
                    };
                    for (int i = 0; i < ClassesArr.length;i++){
                        ClassDescription classToInsert = new ClassDescription();
                        classToInsert.setClassName(ClassesArr[i][0]);
                        classToInsert.setMinDamageMultiplier(Float.parseFloat(ClassesArr[i][1]));
                        classToInsert.setMaxDamageMultiplier(Float.parseFloat(ClassesArr[i][2]));
                        classToInsert.setHealthBonus(Float.parseFloat(ClassesArr[i][3]));
                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().ClassesDao().insert(classToInsert);
                    }
                    Toast.makeText(getApplicationContext(), "База классов загружена", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("Ya","Num is not zero");
                }
            }
        }

        SetClasses gt = new SetClasses();
        gt.execute();

    }*/


}
package com.example.databaselab;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class ArenaHallActivity extends Activity {
    private EditText enterName1, enterName2;
    String fName, sName;
    ClassDescription classOfFighter1 = null, classOfFighter2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arenahall);

        enterName1 = findViewById(R.id.firstFighterName);
        enterName2 = findViewById(R.id.secondFighterName);

        findViewById(R.id.startButton1).setOnClickListener(x -> {
            BeginCombat();
        });

    }

    private void BeginCombat(){
        fName = enterName1.getText().toString();
        sName = enterName2.getText().toString();
        if (fName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Nickname field of first fighter is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nickname field of second fighter is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        getFighters(fName, sName);

        //loadFighters(classOfFighter1, classOfFighter2);

    }


    private void getFighters(String firstName, String secondName) {
        class GetResults extends AsyncTask<Void, Void, List<Player>> {

            @Override
            protected List<Player> doInBackground(Void... voids) {
                List<Player> resultsPlayerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getPlayerPairInfo(firstName, secondName);
                return resultsPlayerList;
            }

            @Override
            protected void onPostExecute(List<Player> res) {
                super.onPostExecute(res);

                Intent intent = new Intent(ArenaHallActivity.this, ArenaFightActivity.class);
                if (res.size() == 2) {
                    intent.putExtra("player1", res.get(0));
                    intent.putExtra("player2", res.get(1));
                } else {
                    finish();
                }

                startActivity(intent);
            }
        }

        GetResults gt = new GetResults();
        gt.execute();
    }

}

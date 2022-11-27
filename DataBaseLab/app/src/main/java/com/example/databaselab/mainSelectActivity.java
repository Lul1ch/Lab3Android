package com.example.databaselab;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class mainSelectActivity extends  Activity{
    String className;
    int minLevel, maxLevel;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_finded);

        className = (String) getIntent().getSerializableExtra("className");
        minLevel = (Integer) getIntent().getSerializableExtra("minLevel");
        maxLevel = (Integer) getIntent().getSerializableExtra("maxLevel");

        recyclerView = findViewById(R.id.rvFounded);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getFoundedPersons();
    }

    private void getFoundedPersons() {
        class getFoundedPersons extends AsyncTask<Void, Void, List<Player>> {

            @Override
            protected List<Player> doInBackground(Void... voids) {
                List<Player> playerList;
                Log.d("Ya", "8" + className + "8");
                if (!Objects.equals(className, "eny")) {
                    if (minLevel >= 0) {
                        if (maxLevel >= 0) {
                            playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByAllConds(className, minLevel, maxLevel);
                            return playerList;
                        } else {
                            playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByClassAndMinLvl(className, minLevel);
                            return playerList;
                        }
                    }
                    if (maxLevel >=0){
                        playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByClassAndMaxLvl(className, maxLevel);
                        return playerList;
                    } else {
                        playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByClass(className);
                        return playerList;
                    }
                } else if (minLevel >= 0){
                    if (maxLevel >= 0){
                        playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByMaxAndMinLvl(minLevel, maxLevel);
                        return playerList;
                    } else {
                        playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByMinLevel(minLevel);
                        return playerList;
                    }
                } else if (maxLevel >= 0){
                    playerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getCertainPlayersByMaxLevel(maxLevel);
                    return playerList;
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Player> player) {
                super.onPostExecute(player);
                PlayerAdapter adapter = new PlayerAdapter(mainSelectActivity.this, player);
                recyclerView.setAdapter(adapter);
            }
        }

        getFoundedPersons gt = new getFoundedPersons();
        gt.execute();
    }

}

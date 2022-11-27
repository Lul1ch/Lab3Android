package com.example.databaselab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
        getPersons();
        //importClasses();
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


}
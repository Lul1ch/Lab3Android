package com.example.databaselab;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ArenaActivity extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combatresults);

        recyclerView = findViewById(R.id.rvCombat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.combat).setOnClickListener(x -> {
            Intent goForward = new Intent(ArenaActivity.this, ArenaHallActivity.class);
            startActivity(goForward);
        });

        getResults();
    }

    private void getResults() {
        class GetResults extends AsyncTask<Void, Void, List<CombatResult>> {
            ArrayList<PlayerDao.PlInfo> dopInfo = new ArrayList<PlayerDao.PlInfo>();
            @Override
            protected List<CombatResult> doInBackground(Void... voids) {
                List<CombatResult> resultsList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().CombatResultsDao().getAll();
                for(int i = 0; i < resultsList.size();i++){
                    if (resultsList.get(i) != null) {
                        List<PlayerDao.PlInfo> tempList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().getFightersInfo(resultsList.get(i).getFNickName(), resultsList.get(i).getSNickName());

                        dopInfo.add(tempList.get(0));
                        dopInfo.add(tempList.get(tempList.size() - 1));
                    }
                }
                return resultsList;
            }

            @Override
            protected void onPostExecute(List<CombatResult> combat) {
                super.onPostExecute(combat);
                //if (combat.get())
                ArenaAdapter adapter = new ArenaAdapter(ArenaActivity.this, combat, dopInfo);
                recyclerView.setAdapter(adapter);
            }
        }

        GetResults gt = new GetResults();
        gt.execute();
    }
}

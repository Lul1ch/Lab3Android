package com.example.databaselab;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;


public class ArenaFightActivity extends  Activity {
    ClassDescription class1 = null, class2 = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        final Player player1 = (Player) getIntent().getSerializableExtra("player1");
        final Player player2 = (Player) getIntent().getSerializableExtra("player2");

        getFighterClasses(player1.getClassName(), player2.getClassName(), player1, player2);
    }

    private void loadFighters(ClassDescription classOfplayer1, ClassDescription classOfplayer2, Player pl1, Player pl2){
        TextView editFighterHp1 = findViewById(R.id.textView1);
        float health1 =new Player().countHealth(classOfplayer1.getHealthBonus(),classOfplayer1.getClassName(), pl1.getLevel());
        float health2 =new Player().countHealth(classOfplayer2.getHealthBonus(),classOfplayer2.getClassName(), pl2.getLevel());
        editFighterHp1.setText(String.valueOf(health1));

        TextView editFighterHp2 = findViewById(R.id.textView2);
        editFighterHp2.setText(String.valueOf(health2));
        //Log.d("Ya", "fH " + new Player().countHealth(classOfplayer2.getHealthBonus()));
        ImageView editFighterCard1 = findViewById(R.id.imageView1);
        editFighterCard1.setImageResource(getDrawableId(classOfplayer1.getClassName()));

        ImageView editFighterCard2 = findViewById(R.id.imageView2);
        editFighterCard2.setImageResource(getDrawableId(classOfplayer2.getClassName()));

        simulateFight(classOfplayer1, classOfplayer2, health1, health2, pl1, pl2);
    }

    private int getDrawableId(String className){
        if (Objects.equals(className, "archer")){
            return R.drawable.archer;
        } else if (Objects.equals(className, "warrior")){
            return R.drawable.warrior;
        } else if (Objects.equals(className, "mage")) {
            return R.drawable.mage;
        }
        return 0;//Ошибка!!
    }

    private void getFighterClasses(String firstName, String secondName, Player pl1, Player pl2) {
        class GetResults extends AsyncTask<Void, Void, List<ClassDescription>> {

            @Override
            protected List<ClassDescription> doInBackground(Void... voids) {
                List<ClassDescription> resultsPlayerList = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().ClassesDao().getClassPairInfo(firstName,secondName);
                //getClassPairInfo(firstName, secondName)
                return resultsPlayerList;
            }

            @Override
            protected void onPostExecute(List<ClassDescription> res) {
                super.onPostExecute(res);
                for (int i = 0; i < res.size();i++){
                    if (res.get(i).getClassName() != null && Objects.equals(res.get(i).getClassName(), pl1.getClassName())){
                        class1 = res.get(i);
                    }
                    if (res.get(i).getClassName() != null && Objects.equals(res.get(i).getClassName(), pl2.getClassName())){
                        class2 = res.get(i);
                    }
                }
                loadFighters(class1,class2, pl1, pl2);
            }
        }

        GetResults gt = new GetResults();
        gt.execute();
    }

    private void simulateFight(ClassDescription cl1, ClassDescription cl2, float health1,float health2, Player pl1, Player pl2){
        TextView log = findViewById(R.id.textLog);
        String res = "Draw";
        while(health1 > 0 && health2 > 0){
            float dmg1 = new Player().countDmg(cl1.getMinDamageMultiplier(), cl1.getMaxDamageMultiplier(), cl1.getClassName(),pl1.getLevel());
            float dmg2 = new Player().countDmg(cl2.getMinDamageMultiplier(), cl2.getMaxDamageMultiplier(), cl2.getClassName(),pl2.getLevel());
            health1 -= dmg2;
            health2 -= dmg1;
            log.setText(log.getText() + "\n" + "First fighter dealt " + dmg1 + "\n" +"Second fighter dealt" + dmg2);
        }

        if (health1 <= 0){
            res = "Second fighter won";
            log.setText(log.getText() + "\n" + res);
        } else if (health2 <= 0){
            res = "First fighter won";
            log.setText(log.getText() + "\n" + res);
        }
        final String finalRes = res;

        class SaveCombat extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                CombatResult combat = new CombatResult();
                combat.setFNickName(pl1.getNickname());
                combat.setSNickName(pl2.getNickname());
                combat.setResult(finalRes);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().CombatResultsDao().insert(combat);
                if (finalRes.equals("Second fighter won")){
                    pl2.setLevel(pl2.getLevel() + 1);
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().update(pl2);
                } else if (finalRes.equals("First fighter won")){
                    pl1.setLevel(pl1.getLevel() + 1);
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().update(pl1);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                findViewById(R.id.finishCombatButton).setOnClickListener(x -> {
                    Intent goForward = new Intent(ArenaFightActivity.this, MainActivity.class);
                    startActivity(goForward);
                });
            }
        }

        SaveCombat svC = new SaveCombat();
        svC.execute();
    }
}



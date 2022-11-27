package com.example.databaselab;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;


public class SecondActivity extends Activity {
    private EditText enterName, enterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        enterName = findViewById(R.id.editPersonName);
        enterClass = findViewById(R.id.editClassName);
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SavePlayer();
                    }
                });
    }

    private void SavePlayer(){
        String userInputNick = enterName.getText().toString();
        String userInputClass = enterClass.getText().toString();
        if (userInputNick.isEmpty()){
            Toast.makeText(getApplicationContext(), "Nickname field is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userInputClass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Class field is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        String template = userInputClass.toLowerCase(Locale.ROOT);
        Toast.makeText(getApplicationContext(), template, Toast.LENGTH_SHORT).show();
        if (!template.equals("mage") && !template.equals("warrior") && !template.equals("archer")){
            Toast.makeText(getApplicationContext(), "Incorrect class name!", Toast.LENGTH_SHORT).show();
            return;
        }

        class SavePlayer extends AsyncTask<Void, Void, Void> {
            Player newbie;
            @Override
            protected Void doInBackground(Void... voids) {

                newbie = new Player();
                newbie.setNickname(userInputNick);
                newbie.setClassName(userInputClass.toLowerCase(Locale.ROOT));
                newbie.setLevel(1);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().insert(newbie);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }


        /*SaveCombat st = new SaveCombat();
        st.execute();*/

        SavePlayer st1 = new SavePlayer();
        st1.execute();

    }
}


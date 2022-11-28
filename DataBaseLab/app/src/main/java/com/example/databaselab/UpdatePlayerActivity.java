package com.example.databaselab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class UpdatePlayerActivity extends Activity {
    private EditText editPlayerName, editPlayerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editPlayerName = findViewById(R.id.editPersonName);
        editPlayerClass = findViewById(R.id.editClassName);

        final Player player = (Player) getIntent().getSerializableExtra("player");

        loadPlayer(player);

        findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlayer(player);
            }
        });

        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePlayerActivity.this);
                builder.setTitle("Вы уверены?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletePlayer(player);
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });

    }
    private void loadPlayer(Player player) {
        editPlayerName.setText(player.getNickname());
        editPlayerClass.setText(player.getClassName());
    }

    private void updatePlayer(final Player player) {
        final String sNickname = editPlayerName.getText().toString().trim();
        final String sClass = editPlayerClass.getText().toString().trim();

        if (sNickname.isEmpty()) {
            editPlayerName.setError("Nickname field is empty!");
            editPlayerName.requestFocus();
            return;
        }

        if (sClass.isEmpty()) {
            editPlayerClass.setError("Class field is empty!");
            editPlayerClass.requestFocus();
            return;
        }

        String template = sClass.toLowerCase(Locale.ROOT);
        if (!template.equals("mage") && !template.equals("warrior") && !template.equals("archer")){
            Toast.makeText(getApplicationContext(), "Incorrect class name!", Toast.LENGTH_SHORT).show();
            return;
        }

        class UpdatePlayer extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                player.setNickname(sNickname);
                player.setClassName(sClass);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().update(player);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Обновлено", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdatePlayerActivity.this, MainActivity.class));
            }
        }

        UpdatePlayer ut = new UpdatePlayer();
        ut.execute();
    }

    private void deletePlayer(final Player player) {
        class DeleteEmployee extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().PlayerDao().delete(player);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Удалено", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdatePlayerActivity.this, MainActivity.class));
            }
        }

        DeleteEmployee dt = new DeleteEmployee();
        dt.execute();
    }
}

package com.example.databaselab;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

public class mainFindActivity extends Activity {
    private EditText classToFind, minLevelToFind, maxLevelToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_find);

        classToFind = findViewById(R.id.classSelect);
        minLevelToFind = findViewById(R.id.minSelectLevel);
        maxLevelToFind = findViewById(R.id.maxSelectLevel);

        findViewById(R.id.selectButton).setOnClickListener(x -> {
            Intent goForward = new Intent(mainFindActivity.this, mainSelectActivity.class);
            String classToSelect = classToFind.getText().toString().toLowerCase(Locale.ROOT);
            int minLevelToSelect = -1, maxLevelToSelect = -1;
            if (!classToSelect.isEmpty()){

                if (!Objects.equals(classToSelect, "mage") && !Objects.equals(classToSelect, "warrior")  && !Objects.equals(classToSelect, "archer") ){
                    Toast.makeText(getApplicationContext(), "Incorrect class name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                goForward.putExtra("className", classToSelect);
            } else {
                goForward.putExtra("className", "eny");
            }
            if (!minLevelToFind.getText().toString().isEmpty()){
                minLevelToSelect = Integer.parseInt(minLevelToFind.getText().toString());
                goForward.putExtra("minLevel", minLevelToSelect);
            } else {
                goForward.putExtra("minLevel", -1);
            }
            if (!maxLevelToFind.getText().toString().isEmpty()){
                maxLevelToSelect = Integer.parseInt(maxLevelToFind.getText().toString());
                goForward.putExtra("maxLevel", maxLevelToSelect);
            } else {
                goForward.putExtra("maxLevel", -1);
            }

            startActivity(goForward);
        });

    }
}

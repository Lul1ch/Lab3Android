package com.example.databaselab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "results")
public class CombatResult {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fFighterName")
    private String fNickName;

    @ColumnInfo(name = "sFighterName")
    private String sNickName;

    @ColumnInfo(name = "result")
    private String result;

    public void setId(int id) {
        this.id = id;
    }

    public void setFNickName(String fNickName) {
        this.fNickName = fNickName;
    }

    public void setSNickName(String sNickName) {
        this.sNickName = sNickName;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public String getFNickName() {
        return fNickName;
    }

    public String getSNickName() {
        return sNickName;
    }

    public String getResult() {
        return result;
    }
}

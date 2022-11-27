package com.example.databaselab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "classes")
public class ClassDescription implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "class")
    protected String className;

    @ColumnInfo(name = "minDmgBonus")
    private float minDamageMultiplier;

    @ColumnInfo(name = "maxDmgBonus")
    private float maxDamageMultiplier;

    @ColumnInfo(name = "healthBonus")
    private float healthBonus;

    public void setId(int id) {
        this.id = id;
    }

    public void setMinDamageMultiplier(float value){
        this.minDamageMultiplier = value;
    }

    public void setMaxDamageMultiplier(float value){
        this.maxDamageMultiplier = value;
    }

    public void setHealthBonus(float value){
        this.healthBonus = value;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public float getMinDamageMultiplier(){
        return minDamageMultiplier;
    }

    public float getMaxDamageMultiplier(){
        return maxDamageMultiplier;
    }

    public float getHealthBonus(){
        return healthBonus;
    }

    public String getClassName() {
        return className;
    }
}

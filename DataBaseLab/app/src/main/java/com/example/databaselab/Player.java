package com.example.databaselab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "players")
public class Player implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nickname")
    private String nickname;

    @ColumnInfo(name = "class")
    private String className;

    @ColumnInfo(name = "level")
    private int level;

    public float countHealth(float healthBonus, String curClassName, int lvl){

        if (Objects.equals(curClassName, "warrior")){
            return (100 + healthBonus*lvl);
        } else if (Objects.equals(curClassName, "archer")){
            return (100 + healthBonus*lvl);
        } else if (Objects.equals(curClassName, "mage")){
            return (100 + healthBonus*lvl);
        }
        return 0;
    }

    public float countDmg(float minBonus, float maxBonus, String curClassName, int lvl){
        float curBonus = (float)(Math.random() * ((maxBonus - minBonus) + 1));
        if (Objects.equals(curClassName, "warrior")){
            return ((5*lvl)*curBonus);
        } else if (Objects.equals(curClassName, "archer")){
            return ((5*lvl)*curBonus);
        } else if (Objects.equals(curClassName, "mage")){
            return ((5*lvl)*curBonus);
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

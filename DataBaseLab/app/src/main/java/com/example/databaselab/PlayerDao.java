package com.example.databaselab;

import android.util.Pair;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface PlayerDao {

    public class PlInfo{

        @ColumnInfo(name = "class")
        public String playerClass;

        @ColumnInfo(name = "level")
        public int playerLevel;

        public void setPlayerClass(String playerClass) {
            this.playerClass = playerClass;
        }

        public void setPlayerLevel(int playerLevel) {
            this.playerLevel = playerLevel;
        }

        public String getPlayerClass() {
            return playerClass;
        }

        public int getPlayerLevel() {
            return playerLevel;
        }
    }



    @Query("SELECT * FROM players")
    List<Player> getAll();

    @Query("SELECT level FROM players WHERE nickname = :name")
    int getLevel(String name);

    @Query("SELECT * FROM players WHERE nickname = :name1 OR nickname = :name2")
    List<Player> getPlayerPairInfo(String name1, String name2);

    @Query("SELECT * FROM players WHERE class = :className")
    List<Player> getCertainPlayersByClass(String className);

    @Query("SELECT * FROM players WHERE level >= :minLevel")
    List<Player> getCertainPlayersByMinLevel(int minLevel);

    @Query("SELECT * FROM players WHERE level <= :maxLevel")
    List<Player> getCertainPlayersByMaxLevel(int maxLevel);

    @Query("SELECT * FROM players WHERE level BETWEEN :minLevel AND :maxLevel")
    List<Player> getCertainPlayersByMaxAndMinLvl(int minLevel, int maxLevel);

    @Query("SELECT * FROM players WHERE class = :className AND level >= :minLevel")
    List<Player> getCertainPlayersByClassAndMinLvl(String className, int minLevel);

    @Query("SELECT * FROM players WHERE class = :className AND level <= :maxLevel")
    List<Player> getCertainPlayersByClassAndMaxLvl(String className, int maxLevel);

    @Query("SELECT * FROM players WHERE class = :className AND level BETWEEN :minLevel AND :maxLevel")
    List<Player> getCertainPlayersByAllConds(String className, int minLevel, int maxLevel);

    @Query("SELECT class, level FROM players " +
            "JOIN results ON players.nickname = results.fFighterName OR players.nickname = results.sFighterName" +
            " WHERE players.nickname = :plName1 OR players.nickname = :plName2")
    List<PlInfo> getFightersInfo(String plName1, String plName2);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);

    @Delete
    void delete(Player player);

    @Update
    void update(Player player);
}

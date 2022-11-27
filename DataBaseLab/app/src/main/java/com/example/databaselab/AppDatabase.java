package com.example.databaselab;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class, ClassDescription.class, CombatResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao PlayerDao();
    public abstract ClassesDao ClassesDao();
    public abstract  CombatResultDao CombatResultsDao();

}

package com.example.databaselab;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CombatResultDao {
    @Query("SELECT * FROM results")
    List<CombatResult> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CombatResult res);

    @Delete
    void delete(CombatResult res);

    @Update
    void update(CombatResult res);
}

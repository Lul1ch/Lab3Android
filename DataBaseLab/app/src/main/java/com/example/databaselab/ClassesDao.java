package com.example.databaselab;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClassesDao {

    @Query("SELECT * FROM classes")
    List<ClassDescription> getAll();

    @Query("SELECT * FROM classes WHERE class = :className1 OR class = :className2")
    List<ClassDescription> getClassPairInfo(String className1, String className2);

    @Query("SELECT minDmgBonus FROM classes")
    int getMinDmgBonus();

    @Query("SELECT maxDmgBonus FROM classes")
    int getMaxDmgBonus();

    @Query("SELECT healthBonus FROM classes")
    int getHealthBonus();

    @Insert
    void insert(ClassDescription classInfo);

    @Delete
    void delete(ClassDescription classInfo);

    @Update
    void update(ClassDescription classInfo);
}

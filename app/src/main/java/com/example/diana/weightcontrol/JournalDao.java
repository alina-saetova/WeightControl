package com.example.diana.weightcontrol;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface JournalDao {
    @Insert
    void insert(Note dataModel);

    @Delete
    void delete(Note dataModel);

    @Query("SELECT * FROM note")
    List<Note> getAllData();

    //пример запроса с выборкой
    @Query("SELECT weight FROM note WHERE id LIKE :id")
    double getPreviousWeight(int id);

    @Query("SELECT COUNT(*) FROM note")
    int getDBSize();

    @Query("SELECT date FROM note WHERE id LIKE :id")
    String  getPreviousDate(int id);

}

package com.example.todolisttest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM databasetask")
    List<DatabaseTask> getAll();

    @Query("SELECT * FROM databasetask")
    LiveData<List<DatabaseTask>> getAllLive();

    @Insert
    void insertAll(DatabaseTask... tasks);

    @Insert
    Completable insertOne(DatabaseTask task);

    @Insert
    void insertTask(DatabaseTask task);

    @Update
    void updateTask(DatabaseTask task);

    @Delete
    void delete(DatabaseTask task);

// OPTIONAL other types of queries that may be needed
//    @Query("SELECT * FROM databasetask WHERE tid IN (:taskIds)")
//    List<DatabaseTask> loadAllByIds(int[] taskIds);
//
//    @Query("SELECT * FROM databasetask WHERE field1 LIKE :value1 AND " +
//            "field2 LIKE :value2 LIMIT 1")
//    DatabaseTask findByWhatever(String var1, String var2);

}

package com.example.todolisttest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database (entities = {DatabaseTask.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}

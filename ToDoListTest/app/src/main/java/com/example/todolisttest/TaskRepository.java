package com.example.todolisttest;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao_repo;
    private LiveData<List<DatabaseTask>> taskList;

    TaskRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao_repo = db.taskDao();
        taskList = taskDao_repo.getAllLive();
    }

    LiveData<List<DatabaseTask>> getTaskList(){
        return taskList;
    }

    void insert(final DatabaseTask task){
        Log.i("Task Repository class", "Hello I will insert now");
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Log.i("Task Repository class", "Executering");
            taskDao_repo.insertOne(task);
        });
    }

}

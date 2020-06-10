package com.example.todolisttest;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepo;

    private LiveData<List<DatabaseTask>> allTasks;

    public TaskViewModel(Application application) {
        super(application);
        mRepo = new TaskRepository(application);
        allTasks = mRepo.getTaskList();
    }

    LiveData<List<DatabaseTask>> getAllTasks(){
        return allTasks;
    }

    void insert(DatabaseTask task) {
        mRepo.insert(task);
        Log.i("TaskViewModel", "Hello I finished");
    }



}

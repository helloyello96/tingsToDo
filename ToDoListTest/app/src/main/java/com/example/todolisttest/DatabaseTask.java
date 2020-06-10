package com.example.todolisttest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DatabaseTask {
    @PrimaryKey(autoGenerate = true)
    private int tid;

    @ColumnInfo(name = "task_text")
    private String taskText;

    @ColumnInfo(name = "task_color")
    private int taskColor;

    public int getTid(){
        return this.tid;
    }

    public String getTaskText(){
        return this.taskText;
    }

    public int getTaskColor(){
        return this.taskColor;
    }

    public void setTaskColor(int taskColor) {
        this.taskColor = taskColor;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    // I don't think I need this bc it auto generates... no?
    public void setTid(int tid) {
        this.tid = tid;
    }
}

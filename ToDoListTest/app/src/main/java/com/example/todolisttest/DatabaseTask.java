package com.example.todolisttest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DatabaseTask {
    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "task_text")
    public String taskText;

    @ColumnInfo(name = "task_color")
    public int taskColor;

}

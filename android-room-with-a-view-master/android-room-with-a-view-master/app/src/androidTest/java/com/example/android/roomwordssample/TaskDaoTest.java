package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TaskDao mTaskDao;
    private TaskRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(context, TaskRoomDatabase.class)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build();
        mTaskDao = mDb.taskDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetWord() throws Exception {
        Task task = new Task("word", 1);
        mTaskDao.insert(task);
        List<Task> allTasks = LiveDataTestUtil.getValue(mTaskDao.getSavedTasks());
        assertEquals(allTasks.get(0).getTask(), task.getTask());
    }

    @Test
    public void getAllWords() throws Exception {
        Task task = new Task("aaa",1 );
        mTaskDao.insert(task);
        Task task2 = new Task("bbb",1 );
        mTaskDao.insert(task2);
        List<Task> allTasks = LiveDataTestUtil.getValue(mTaskDao.getSavedTasks());
        assertEquals(allTasks.get(0).getTask(), task.getTask());
        assertEquals(allTasks.get(1).getTask(), task2.getTask());
    }

    @Test
    public void deleteAll() throws Exception {
        Task task = new Task("word",1 );
        mTaskDao.insert(task);
        Task task2 = new Task("word2",1 );
        mTaskDao.insert(task2);
        mTaskDao.deleteAll();
        List<Task> allTasks = LiveDataTestUtil.getValue(mTaskDao.getSavedTasks());
        assertTrue(allTasks.isEmpty());
    }
}

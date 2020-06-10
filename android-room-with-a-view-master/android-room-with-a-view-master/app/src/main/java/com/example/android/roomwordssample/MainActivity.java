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

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskListAdapter adapter = new TaskListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                // Update the cached copy of the words in the adapter.
                adapter.setTasks(tasks);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog myDialog = createNewTaskDialog();
                myDialog.show();
//                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
//                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onDialogOkay(String taskText) {

        if (!taskText.isEmpty()) {
            Task task = new Task(taskText, 1);
            mTaskViewModel.insert(task);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    public AlertDialog createNewTaskDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater (the style inflater)
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View newTaskView = inflater.inflate(R.layout.create_new_task, null);
        final EditText newTaskText = newTaskView.findViewById(R.id.new_task_text);

        builder.setView(newTaskView)
                // The handler for create clicker
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Create New Task Dialog", "Create button was clicked");
                        String value = newTaskText.getText().toString();

                        //To test - it works!!!
                        Log.i("Create New Task Dialog", "Text: " + value);
                        onDialogOkay("This is a test");
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Create New Task Dialog", "Cancel button was clicked");
                        String value = newTaskText.getText().toString();

                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private Window requireActivity() {
        return getWindow();
    }

    private Context getActivity() {
        return this;
    }
}

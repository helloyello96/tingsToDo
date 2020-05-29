package com.example.todolisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide the darn title bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId) {
            case R.id.color_picker_icon :
                // open color picker
                Log.i("onclick", "color picker tapped");
                break;
            case R.id.add_task_icon :
                // add new task
                Log.i("onclick", "add task tapped");
                break;
        }
    }


    // add new task
    public void createNewTask() {
        // click new task button (handler)
        // open text box
        // save
    }


    // change color
        //open color picker


}

package com.example.todolisttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hides the darn title bar
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
                createNewTask();
                break;
            default:
                Log.i("onclick","no function for this viewId " + viewId);
                break;
        }
    }


    /**
     ** Method to handle create new task button
     ** Has a helper method just below to create the dialog prompt
     */
    public void createNewTask() {
        Log.i("Create new task method", "we have entered");
        // Step 1/3: click new task button (handler) <-- handled in the onClick method
        // Step 2/3: open text box
        // this is important or else it doesn't frickin appear!!!
        AlertDialog newTaskDialog = createNewTaskDialog();
        newTaskDialog.show();
        // Step 3/3:save

        //To make sure we finished!
        Log.i("Create new task method", "we have alerted");
    }
    // create new task dialog box (helper function for create new task)
    public AlertDialog createNewTaskDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater (the style inflater)
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View newTaskView = inflater.inflate(R.layout.create_new_task, null);
        final EditText newTaskText = (EditText) newTaskView.findViewById(R.id.new_task_text);


        builder.setView(newTaskView)
                // The handler for create clicker
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Create New Task Dialog", "Create button was clicked");

                        String value = newTaskText.getText().toString();

                        //To test - it works!!!
                        Log.i("Create New Task Dialog", "Text: " + value);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Create New Task Dialog", "Cancel button was clicked");
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }


    // Extras to handle contextual asks
    private Window requireActivity() {
        return getWindow();
    }

    private Context getActivity() {
        return this;
    }


    // change color
        //open color picker


}

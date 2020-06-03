package com.example.todolisttest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    //Initialize the database
    AppDatabase db;
    String newTaskColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hides the darn title bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Task Database").build();


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
        // Step 1/3: (DONE) click new task button (handler) <-- handled in the onClick method
        // Step 2/3: (DONE) open text box
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
        final View newTaskView = inflater.inflate(R.layout.create_new_task, null);
        final EditText newTaskText = newTaskView.findViewById(R.id.new_task_text);
        final RadioGroup colorBar = newTaskView.findViewById(R.id.select_color);

        builder.setView(newTaskView)
                // The handler for create clicker
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Create New Task Dialog", "Create button was clicked");
                        String value = newTaskText.getText().toString();
                        int selectedColor = colorBar.getCheckedRadioButtonId();

                        if(selectedColor == -1){
                            Log.i("Color Bar", "No color was selected");
                        }
                        else{
                            Log.i("Color Bar", "Color id selected: " + selectedColor);
                            CheckBox newTask = makeCheckbox(value, selectedColor);

                            //Set margin so that it doesn't stick to the task above it
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                                    (ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);

                            params.setMargins(0,5,0,0);
                            LinearLayout taskList = findViewById(R.id.list_layout);
                            taskList.addView(newTask, params);
                        }

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CheckBox makeCheckbox(String text, int radioColorId){
        Log.i("Create Checkbox", "Method has been called");
        int taskColor = -1;
        CheckBox newTask = new CheckBox(getActivity());

        // Set width, height, text and font
        ViewGroup.LayoutParams checkboxLayout;
        checkboxLayout = new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        newTask.setLayoutParams(checkboxLayout);
        newTask.setText(text);
        newTask.setTypeface(getResources().getFont(R.font.raleway_regular));

        // Figure out which color to use
        switch(radioColorId) {
            case R.id.color_button1:
                Log.i("Color Radio Clicked","Color1");
                newTask.setBackground(getDrawable(R.drawable.checkbox_style));
                taskColor = 1;
                break;
            case R.id.color_button2:
                newTask.setBackground(getDrawable(R.drawable.checkbox_style2));
                taskColor = 2;
                break;
        }

        // Save new task to database
//        DatabaseTask newDbTask = new DatabaseTask();
//        newDbTask.taskText = text;
//        newDbTask.taskColor = taskColor;
//        db.taskDao().insertOne(newDbTask);

        Log.i("Create Checkbox", "User chose this color: " + taskColor);
        return newTask;
    }


    // Extras to handle contextual asks
    private Window requireActivity() {
        return getWindow();
    }

    private Context getActivity() {
        return this;
    }


    //I don't actually need this... I think
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.color_button1:
                if (checked)
                    Log.i("Color Radio Clicked","Color1");
                break;
            case R.id.color_button2:
                if (checked)
                    Log.i("Color Radio Clicked","Color2");
                break;
        }
    }

    // change color
        //open color picker


}

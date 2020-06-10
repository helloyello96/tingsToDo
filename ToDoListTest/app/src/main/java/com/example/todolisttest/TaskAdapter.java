package com.example.todolisttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private TaskViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.list_layout);
        }
    }

    private final LayoutInflater mInflater;
    private List<DatabaseTask> mTasks; // Cached copy of words

    TaskAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_main, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        if (mTasks != null) {
            DatabaseTask current = mTasks.get(position);
            holder.wordItemView.setText(current.getTaskText());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Data");
        }
    }

    void setWords(List<DatabaseTask> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTasks != null)
            return mTasks.size();
        else return 0;
    }
}

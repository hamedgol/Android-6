package com.codeday.detroit.taskmanager.app.adapters;

import android.content.Context;
import android.graphics.Point;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.codeday.detroit.taskmanager.app.R;
import com.codeday.detroit.taskmanager.app.domain.TaskList;

import java.util.List;

/**
 * Created by kevin on 5/24/14.
 */
public class TaskListAdapter extends BaseAdapter {

    List<TaskList> taskLists;
    Context ctxt;
    LayoutInflater layoutInflater;

    public TaskListAdapter(List<TaskList> t, Context c) {
        taskLists = t;
        ctxt = c;
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return taskLists.size();
    }

    @Override
    public Object getItem(int position) {
        return taskLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            //view = layoutInflater.inflate(R.layout., parent, false);
        }
        //inflate view
        view = layoutInflater.inflate(R.layout.adapter_tasklist_item, parent, false);
        TaskList currentTaskList = taskLists.get(position);
        //set list name
        TextView textViewName = (TextView) view.findViewById(R.id.taskListItemNumber);
        textViewName.setText(currentTaskList.name);
        //set list task amount
        TextView textViewNumber = (TextView) view.findViewById(R.id.taskListItemNumber);
        int numberOfTasksCompleted = currentTaskList.numberOfCompletedTasks;
        int numberOfTasksTotal = currentTaskList.numberOfTasks;
        String remainingTasks = numberOfTasksCompleted + "/" + numberOfTasksTotal;
        textViewNumber.setText(remainingTasks);

        //get screen height and width
        WindowManager wm = (WindowManager) ctxt.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        //int screenHeight = size.y;

        //set new width for progress bar layout
        LinearLayout taskProgressBar = (LinearLayout) view.findViewById(R.id.taskProgressBar);
        ViewGroup.LayoutParams params = taskProgressBar.getLayoutParams();
        params.width = screenWidth * (numberOfTasksCompleted / numberOfTasksTotal);
        view.setLayoutParams(params);

        return view;
    }
}

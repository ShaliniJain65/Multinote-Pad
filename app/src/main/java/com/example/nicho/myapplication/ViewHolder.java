package com.example.nicho.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shalini on 2/9/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public TextView date;
    public TextView description;

    public ViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.title);
        date = (TextView) view.findViewById(R.id.date);
        description = (TextView) view.findViewById(R.id.description);
    }


}

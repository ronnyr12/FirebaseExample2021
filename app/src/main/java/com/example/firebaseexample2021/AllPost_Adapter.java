package com.example.firebaseexample2021;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AllPost_Adapter extends ArrayAdapter<Post> {
    Context context;
    List<Post> posts;


    public AllPost_Adapter(@NonNull Context context, int resource, int textViewResourceId, List<Post> posts) {
        super(context, resource, textViewResourceId, posts);

        this.context = context;
        this.posts = posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).
                getLayoutInflater();

        View view = layoutInflater.inflate(
                R.layout.single_post_row, parent, false);

        TextView tv_title = view.findViewById(R.id.tvTitle);
        Post tmp = posts.get(position);
        tv_title.setText(tmp.title);

        return view;
    }
}

package com.example.jarvis.notejarvis.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jarvis.notejarvis.R;
import com.example.jarvis.notejarvis.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.MyHolder> {
    private List<Note> noteList;
    private LayoutInflater inflater;
    public MyRecyclerView(Context context,List<Note> noteList)
    {
        this.noteList=noteList;
        inflater=LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.note_row,viewGroup);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Note note=noteList.get(i);

        myHolder.textView_note.setText(note.getNote());
        myHolder.textView_date.setText(String.valueOf(note.getDate()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.textView_recycler_row_heder)
        TextView textView_date;
        @BindView(R.id.textView_recycler_row_detail)
        TextView textView_note;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

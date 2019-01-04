package com.example.jarvis.notejarvis.view;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.jarvis.notejarvis.Adapter.MyRecyclerView;
import com.example.jarvis.notejarvis.R;
import com.example.jarvis.notejarvis.model.Note;
import com.example.jarvis.notejarvis.precenter.IProvideData;
import com.example.jarvis.notejarvis.precenter.Precenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements IProvideData {

    @BindView(R.id.recycler_note)
    RecyclerView recycler_note;

    @BindView(R.id.txt_note)
    EditText txt_note;
    private Precenter precenter;
    private MyRecyclerView myRecyclerView;
    public List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteList=new ArrayList<>();
        Note note=new Note();
        ButterKnife.bind(this);

        precenter = new Precenter(this, this);


    }

    private void setupRecycler(List<Note> notes) {
        myRecyclerView = new MyRecyclerView(this, notes);
        recycler_note.setLayoutManager(new LinearLayoutManager(this));
        recycler_note.setAdapter(myRecyclerView);
    }

    @OnClick(R.id.img_add)
    public void onClick_Img_Add() {
        String str_note = txt_note.getText().toString();
        Long date = System.currentTimeMillis();
        precenter.GetDataFromViewForInsert(str_note, date);

        precenter.refreshData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            /*SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Toast.makeText(MainActivity.this, "On Submit is true", Toast.LENGTH_LONG).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Toast.makeText(MainActivity.this, "On Changed is true", Toast.LENGTH_LONG).show();
                    return false;
                }
            });*/
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Exit) {
            System.exit(1);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void getNotesFromPrecenter(List<Note> notes) {
        for (Note note : notes) {
            noteList.add(note);
            Log.d("MainActivity", "-------------------MainActivity----------" + note.getNote());
        }
        setupRecycler(notes);
    }
}

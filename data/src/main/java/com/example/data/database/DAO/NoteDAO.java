package com.example.data.database.DAO;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.data.database.Interface.DaggerIDatabaseComponent;
import com.example.data.database.module.ContextModule;
import com.example.data.database.module.DatabaseModule;
import com.example.data.model.ANote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class NoteDAO {

    @Inject
    Realm realm;

    public NoteDAO(Context context)
    {
        DaggerIDatabaseComponent.builder()
                .contextModule(new ContextModule(context))
                .databaseModule(new DatabaseModule())
                .build().injectRealm(this);
    }
    public List<ANote> getNotes()
    {
        RealmResults<ANote> results=realm.where(ANote.class).findAll();
        List<ANote> aNotes=new ArrayList<>();
        if(results!=null)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                results.parallelStream()
                        .forEach(aNote -> {
                            aNotes.add(aNote);
                        });
            }else
            {
                for (ANote aNote:results)
                {
                    aNotes.add(aNote);
                }
            }
            Log.d("NoteDAO","Fill list success full...........................");
            return aNotes;
        }
        Log.e("NoteDAO","rezult was null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return null;
    }
    public void insertNote(ANote aNote)
    {
        realm.executeTransactionAsync(realm -> {
            ANote objANote=realm.createObject(ANote.class,aNote.getNote());
            objANote.setDate(aNote.getDate());
        }, () -> Log.d("NoteDAO","inserting success ful to realm-------------------------"),
                error -> Log.e("NoteDAO",error.getMessage()));
    }
}

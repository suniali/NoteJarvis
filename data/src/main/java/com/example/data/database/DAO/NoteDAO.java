package com.example.data.database.DAO;

import android.content.Context;
import android.util.Log;

import com.example.data.database.Interface.DaggerIDatabaseComponent;
import com.example.data.database.module.ContextModule;
import com.example.data.database.module.DatabaseModule;
import com.example.data.model.ANote;

import javax.inject.Inject;

import io.realm.Realm;

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
    public void insertNote(ANote aNote)
    {
        realm.executeTransactionAsync(realm -> {
            ANote objANote=realm.createObject(ANote.class,aNote.getNote());
            objANote.setDate(aNote.getDate());
        }, () -> Log.d("NoteDAO","inserting success ful to realm-------------------------"),
                error -> Log.e("NoteDAO",error.getMessage()));
    }
}

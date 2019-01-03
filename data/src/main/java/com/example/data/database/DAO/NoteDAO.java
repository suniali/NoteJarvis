package com.example.data.database.DAO;

import android.content.Context;

import com.example.data.database.Interface.DaggerIDatabaseComponent;
import com.example.data.database.module.ContextModule;
import com.example.data.database.module.DatabaseModule;

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
}

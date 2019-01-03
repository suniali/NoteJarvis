package com.example.data.database.Interface;

import com.example.data.database.DAO.NoteDAO;
import com.example.data.database.module.DatabaseModule;
import com.example.data.database.realm;

import dagger.Component;

@Component(modules = DatabaseModule.class)
@realm
public interface IDatabaseComponent {
    void injectRealm(NoteDAO noteDAO);
}

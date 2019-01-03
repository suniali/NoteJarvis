package com.example.data.database.module;

import android.content.Context;

import com.example.data.database.realm;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Provides
    @realm
    public Realm provideRealm(Context context)
    {
        Realm.init(context);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name("note.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(configuration);
    }
}

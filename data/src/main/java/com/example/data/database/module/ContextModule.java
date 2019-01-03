package com.example.data.database.module;

import android.content.Context;

import com.example.data.database.realm;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;
    public ContextModule(Context context)
    {
        this.context=context;
    }

    @Provides
    @realm
    public Context getContext()
    {
        return context;
    }
}

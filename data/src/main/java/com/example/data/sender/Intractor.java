package com.example.data.sender;

import android.content.Context;

import com.example.domain.model.DNote;
import com.example.domain.repository.IRepository;

import java.util.List;

public class Intractor implements IRepository {
    public Intractor(Context context)
    {
        
    }
    @Override
    public List<DNote> getNotesFromApi() {
        return null;
    }

    @Override
    public void getDataForInsert(String note, Long date) {
        insertToRealm(note,date);
    }

    private void insertToRealm(String note, Long date) {
    }
}

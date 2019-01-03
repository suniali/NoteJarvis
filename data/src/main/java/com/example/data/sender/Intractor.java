package com.example.data.sender;

import android.content.Context;

import com.example.data.database.DAO.NoteDAO;
import com.example.data.model.ANote;
import com.example.domain.model.DNote;
import com.example.domain.repository.IRepository;

import java.util.List;

public class Intractor implements IRepository {
    private NoteDAO noteDAO;
    public Intractor(Context context)
    {
        noteDAO=new NoteDAO(context);
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
        ANote aNote=new ANote();
        aNote.setDate(date);
        aNote.setNote(note);
        noteDAO.insertNote(aNote);
    }
}

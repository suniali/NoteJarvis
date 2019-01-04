package com.example.data.sender;

import android.content.Context;
import android.util.Log;

import com.example.data.Mapper.MapToDNote;
import com.example.data.database.DAO.NoteDAO;
import com.example.data.model.ANote;
import com.example.domain.model.DNote;
import com.example.domain.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Intractor implements IRepository {
    private NoteDAO noteDAO;
    public Intractor(Context context)
    {
        noteDAO=new NoteDAO(context);
    }

    @Override
    public List<DNote> getNotesFromApi() {
        return mapANoteToDNote(noteDAO.getNotes());
    }

    private List<DNote> mapANoteToDNote(List<ANote> notes) {
        MapToDNote mapToDNote=new MapToDNote();
        List<DNote> dNotes=new ArrayList<>();
        for (ANote aNote:notes) {
            DNote dNote=new DNote();    // TODO: 1/4/2019 why we can't use observable for ralm model and map othe class? 
            dNote.setDate(aNote.getDate());
            dNote.setNote(aNote.getNote());
            dNotes.add(dNote);
            Log.d("Intractor", "----------Intractor----------" + aNote.getNote());
        }

        return mapToDNote.transform(notes);
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

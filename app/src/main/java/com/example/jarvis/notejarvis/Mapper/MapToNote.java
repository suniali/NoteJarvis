package com.example.jarvis.notejarvis.Mapper;

import android.os.Build;

import com.example.domain.model.DNote;
import com.example.jarvis.notejarvis.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MapToNote {
    public Note transform(DNote dNote)
    {
        if(dNote==null)
        {
            throw new IllegalArgumentException("error to mapping dnote is null");
        }
        Note note=new Note();
        note.setDate(dNote.getDate());
        note.setNote(dNote.getNote());
        return note;
    }
    public List<Note> transform(List<DNote> dNotes)
    {
        List<Note> notes=new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dNotes.parallelStream()
                    .forEach(dNote -> notes.add(transform(dNote)));
        }else
        {
            for (DNote dNote:dNotes)
            {
                notes.add(transform(dNote));
            }
        }
        return notes;
    }
}

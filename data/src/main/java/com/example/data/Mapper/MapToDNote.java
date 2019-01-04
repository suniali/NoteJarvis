package com.example.data.Mapper;

import android.os.Build;

import com.example.data.model.ANote;
import com.example.domain.model.DNote;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MapToDNote {
    public DNote transform(ANote aNote)
    {
        if(aNote==null)
        {
            throw new IllegalArgumentException("error to mapping anote this is null!!!!!!!!!!!11");
        }
        DNote dNote=new DNote();
        dNote.setNote(aNote.getNote());
        dNote.setDate(aNote.getDate());
        return dNote;
    }
    public List<DNote> transform(List<ANote> aNotes)
    {
        List<DNote> dNoteList=new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            aNotes.parallelStream()
                    .forEach(aNote -> {
                        dNoteList.add(transform(aNote));
                    });
        }else
        {
            for (ANote aNote:aNotes)
            {
                dNoteList.add(transform(aNote));
            }
        }
        return dNoteList;
    }
}

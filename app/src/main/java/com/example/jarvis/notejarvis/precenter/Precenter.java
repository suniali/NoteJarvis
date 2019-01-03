package com.example.jarvis.notejarvis.precenter;

import android.content.Context;

import com.example.data.sender.Intractor;
import com.example.domain.Intractor.GetNotes;
import com.example.domain.UseCase.IUseCase;
import com.example.domain.model.DNote;

import java.util.List;

public class Precenter implements IUseCase.callback<DNote>,IFireData {
    private GetNotes getNotes;
    private Intractor intractor;
    public Precenter(Context context)
    {
        intractor=new Intractor(context);
        getNotes=new GetNotes(intractor);
    }
    @Override
    public void getNotes(List<DNote> list) {

    }

    @Override
    public void GetDataFromViewForInsert(String note, Long date) {
        getNotes.getDataFromPrecenterForInsert(note,date);
    }
}

package com.example.jarvis.notejarvis.precenter;

import android.content.Context;

import com.example.domain.Intractor.GetNotes;
import com.example.domain.UseCase.IUseCase;
import com.example.domain.model.DNote;

import java.util.List;

public class Precenter implements IUseCase.callback<DNote> {
    private GetNotes getNotes;
    public Precenter(Context context)
    {

    }
    @Override
    public void getNotes(List<DNote> list) {

    }
}

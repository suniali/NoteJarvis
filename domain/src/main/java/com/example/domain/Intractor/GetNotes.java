package com.example.domain.Intractor;

import com.example.domain.UseCase.IUseCase;
import com.example.domain.model.DNote;
import com.example.domain.repository.IRepository;

import java.util.List;


public class GetNotes implements IUseCase<DNote> {
    private IRepository iRepository;
    public GetNotes(IRepository iRepository)
    {
        this.iRepository=iRepository;
    }
    @Override
    public void execute(callback callback) {
        List<DNote> list=iRepository.getNotesFromApi();
        for (DNote dNote:list)
        {
            System.out.println("-------------use case--------------------"+dNote.getNote());
        }
        callback.getNotes(iRepository.getNotesFromApi());
    }

    @Override
    public void getDataFromPrecenterForInsert(String note, Long date) {
        iRepository.getDataForInsert(note,date);
    }
}

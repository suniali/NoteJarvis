package com.example.domain.repository;

import com.example.domain.model.DNote;

import java.util.List;

public interface IRepository {
    List<DNote> getNotesFromApi();

    void getDataForInsert(String note, Long date);
}

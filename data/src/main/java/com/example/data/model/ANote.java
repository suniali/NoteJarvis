package com.example.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ANote extends RealmObject {
    private Long date;
    @PrimaryKey
    private String note;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

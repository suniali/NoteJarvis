package com.example.jarvis.notejarvis.precenter;

import android.content.Context;
import android.util.Log;

import com.example.data.sender.Intractor;
import com.example.domain.Intractor.GetNotes;
import com.example.domain.UseCase.IUseCase;
import com.example.domain.model.DNote;
import com.example.jarvis.notejarvis.Mapper.MapToNote;
import com.example.jarvis.notejarvis.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableInterval;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Precenter implements IUseCase.callback<DNote>, IFireData {
    private GetNotes getNotes;
    private Intractor intractor;
    private IProvideData iProvideData;

    public Precenter(Context context, IProvideData iProvideData) {
        this.iProvideData = iProvideData;
        intractor = new Intractor(context);
        getNotes = new GetNotes(intractor);
        getNotes.execute(this);
    }

    public void refreshData() {

        getNotes.execute(this);
    }

    @Override
    public void getNotes(List<DNote> list) {
        List<Note> notes = new ArrayList<>();
        for (DNote dNote : list) {
            Note note = new Note();
            note.setNote(dNote.getNote());
            note.setDate(dNote.getDate());
            notes.add(note);
            Log.d("Precenter", "------------------" + dNote.getNote());
        }
        //iProvideData.getNotesFromPrecenter(notes);
        Observable<List<DNote>> listObservable = Observable.create(emitter -> {
            emitter.onNext(list);
            emitter.onComplete();
        });
        MapToNote mapToNote = new MapToNote();
        listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(dNotes -> mapToNote.transform(dNotes))
                .subscribe(new Observer<List<Note>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Note> notes) {
                        iProvideData.getNotesFromPrecenter(notes);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void GetDataFromViewForInsert(String note, Long date) {
        getNotes.getDataFromPrecenterForInsert(note, date);
    }
}

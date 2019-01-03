package com.example.domain.UseCase;

import java.util.List;

public interface IUseCase<N> {

    interface callback<N>
    {
        void getNotes(List<N> list);
    }
    void execute(callback callback);
}

package com.example.superhero;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CarregaSuperpoder extends AsyncTaskLoader<String> {
    private String mQueryString;

    public CarregaSuperpoder(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return BuscaSuper.buscapoder(mQueryString);
    }
}

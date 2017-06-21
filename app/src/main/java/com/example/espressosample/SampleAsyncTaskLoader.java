package com.example.espressosample;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Андрей on 21.06.2017.
 */

public class SampleAsyncTaskLoader extends AsyncTaskLoader<String> {
    public SampleAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Test String";
    }
}

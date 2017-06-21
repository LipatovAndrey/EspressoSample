package com.example.espressosample;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int LOADER_ID = 36;
    @VisibleForTesting
    protected boolean mIsLoaded = false;

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportLoaderManager().initLoader(LOADER_ID, null, new SampleLoaderCallBacks());
            }
        });

    }

    private class SampleLoaderCallBacks implements LoaderManager.LoaderCallbacks<String> {

        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            return new SampleAsyncTaskLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            mTextView.setText(data);
            mIsLoaded = true;
        }


        @Override
        public void onLoaderReset(Loader loader) {

        }
    }
}

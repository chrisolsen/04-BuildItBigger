package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.chrisolsen.backend.myApi.MyApi;
import org.chrisolsen.common.Joke;

import java.io.IOException;

public class JokeTask extends AsyncTask<Void, Void, Joke> {

    public interface JokeTaskListener {
        void onJokeReceived(Joke joke);
    }

    private static final String TAG = "JokeTask";
    private JokeTaskListener mJokeListener;
    private MyApi mApiService;

    public JokeTask(JokeTaskListener listener) {
        mJokeListener = listener;
    }

    @Override
    protected Joke doInBackground(Void... voids) {
        if(mApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            mApiService = builder.build();
        }

        try {
            String text = mApiService.getJoke().execute().getData();
            Log.d(TAG, "doInBackground: " + text);
            return new Joke(text);
        } catch (IOException e) {
            Log.d(TAG, "fetch: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Joke joke) {
        Log.d(TAG, "onPostExecute: JOKE: " + joke);
        mJokeListener.onJokeReceived(joke);
    }
}
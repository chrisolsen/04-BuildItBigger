package org.chrisolsen.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.chrisolsen.common.Joke;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_PARCELABLE_EXTRA_KEY = "JokeParcelableExtra";
    private static final String TAG = "JokeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Log.d(TAG, "onCreate: about to get the intent");
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "A joke passed in via an intent is required", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "onCreate: getParcelableExtra");
        Joke joke = intent.getParcelableExtra(JOKE_PARCELABLE_EXTRA_KEY);

        if (joke == null) {
            Toast.makeText(this, "No joke!", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView jokeText = (TextView)findViewById(R.id.joke);
        jokeText.setText(joke.toString());
    }
}

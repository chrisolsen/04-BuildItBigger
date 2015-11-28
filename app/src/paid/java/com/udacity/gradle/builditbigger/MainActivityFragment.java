package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.JokeTask.JokeTaskListener;

import org.chrisolsen.android.JokeActivity;
import org.chrisolsen.common.Joke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeTaskListener {

    ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.spinner);

        bindTellJokeButton(view);

        return view;
    }

    private void bindTellJokeButton(View root) {
        final JokeTaskListener jokeTaskListener = this;
        Button btn = (Button) root.findViewById(R.id.btnTellJoke);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                new JokeTask(jokeTaskListener).execute();
            }
        });
    }

    @Override
    public void onJokeReceived(Joke joke) {
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_PARCELABLE_EXTRA_KEY, joke);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        mProgressBar.setVisibility(View.GONE);
    }
}

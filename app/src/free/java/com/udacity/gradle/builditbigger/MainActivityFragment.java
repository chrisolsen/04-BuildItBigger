package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.chrisolsen.android.JokeActivity;
import org.chrisolsen.common.Joke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeTask.JokeTaskListener {

    InterstitialAd mInterstitialAd;
    AdView mAdView;
    ProgressBar mProgressBar;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final JokeTask.JokeTaskListener jokeTaskListener = this;
        mProgressBar = (ProgressBar) root.findViewById(R.id.spinner);

        bindTellJokeButton(root);
        initEmbeddedAd(root);
        initInterstitialAd(root, new AdListener() {
            @Override
            public void onAdClosed() {
                new JokeTask(jokeTaskListener).execute();
            }

            @Override
            public void onAdOpened() {
                mProgressBar.setVisibility(View.GONE);
            }
        });

        return root;
    }

    private void bindTellJokeButton(View root) {
        final JokeTask.JokeTaskListener listener = this;
        Button btn = (Button) root.findViewById(R.id.btnTellJoke);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    new JokeTask(listener).execute();
                }
            }
        });
    }

    private void initEmbeddedAd(View root) {
        mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(getString(R.string.device_id))
                .build();
        mAdView.loadAd(adRequest);
    }

    private void initInterstitialAd(View root, AdListener adListener) {
        String adId = getResources().getString(R.string.banner_ad_unit_id);
        String deviceId = getResources().getString(R.string.device_id);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(adId);
        mInterstitialAd.setAdListener(adListener);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(deviceId)
                .build();

        mInterstitialAd.loadAd(adRequest);
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

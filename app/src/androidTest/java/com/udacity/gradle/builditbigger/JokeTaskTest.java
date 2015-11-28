package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import org.chrisolsen.common.Joke;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JokeTaskTest extends AndroidTestCase {

    private static final String TAG = "JokeTaskTest";
    private CountDownLatch signal = null;

    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "setUp:");
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testTheAsyncTask() throws Throwable {
        final JokeTask task = new JokeTask(new JokeTask.JokeTaskListener() {
            @Override
            public void onJokeReceived(Joke joke) {
                assertTrue(true);
                signal.countDown();
            }
        });

        task.execute();
        signal.await(10, TimeUnit.SECONDS);
    }
}

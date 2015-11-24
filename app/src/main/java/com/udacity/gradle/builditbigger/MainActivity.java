package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.chrisolsen.android.JokeActivity;
import org.chrisolsen.common.Joke;
import org.chrisolsen.common.JokeProvider;


public class MainActivity extends AppCompatActivity {

    JokeProvider mJokeProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJokeProvider = new JokeProvider();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        new JokeTask(this).execute();
    }

    class JokeTask extends AsyncTask<Void, Void, Joke> {

        private Context mContext;

        public JokeTask(Context c) {
            mContext = c;
        }

        @Override
        protected Joke doInBackground(Void... voids) {
            return mJokeProvider.fetch();
        }

        @Override
        protected void onPostExecute(Joke joke) {
            Intent intent = new Intent(mContext, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_PARCELABLE_EXTRA_KEY, joke);
            startActivity(intent);
        }
    }




}

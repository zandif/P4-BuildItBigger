package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.async.AsyncJoke;
import com.udacity.gradle.builditbigger.interfaces.JokeCallback;
import com.udacity.gradle.jokes.JokeProvider;

import net.vectortime.jokedisplay.JokeActivity;


public class MainActivity extends ActionBarActivity implements JokeCallback{
    private AsyncJoke mAsyncJoke;
    private InterstitialAd mInterstitialAd;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAsyncJoke = new AsyncJoke(this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.testAdMobUnitID));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJokeAsync(mView);
            }
        });

        requestNewInterstitial();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
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

    public void tellJoke(View view) {
        JokeProvider jokeProvider = new JokeProvider();
        String joke = jokeProvider.getJoke();
//        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().popBackStack();
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

    public void tellJokeAsync(View view) {
        mView = view;
        ProgressBar spinner = (ProgressBar) findViewById(R.id.loadingSpinner);
        spinner.setVisibility(View.VISIBLE);

        Button button = (Button) findViewById(R.id.tellJokeButton);
        if (button != null) { button.setEnabled(false); }

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mAsyncJoke.tellJoke(this);
        }
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchJokeIntent(String joke) {
        // Stop the progress spinner
        ProgressBar spinner = (ProgressBar) findViewById(R.id.loadingSpinner);
        if (spinner != null) {
            spinner.setVisibility(View.GONE);
        }

        // Re-enable the button
        Button button = (Button) findViewById(R.id.tellJokeButton);
        if (button != null) {
            button.setEnabled(true);
        }

        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }
}

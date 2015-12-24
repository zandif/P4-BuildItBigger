package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import com.udacity.gradle.builditbigger.async.AsyncJoke;
import com.udacity.gradle.builditbigger.interfaces.JokeCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin on 12/23/2015.
 */
public class AsyncTest extends AndroidTestCase implements JokeCallback{
    AsyncJoke mAsyncJoke;
    CountDownLatch mSignal = new CountDownLatch(1); // http://www.making-software.com/2012/10/31/testable-android-asynctask/

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAsyncJoke = new AsyncJoke(null);
    }

    public void testJokeRetrieval() throws InterruptedException {
//        assertEquals("hello", Echo.echo("hello"));

        mAsyncJoke.tellJoke(this);
        mSignal.await(30, TimeUnit.SECONDS);
    }

    @Override
    public void showErrorMessage(String error) {
        fail(error);
    }

    @Override
    public void launchJokeIntent(String joke) {
        assertNotNull(joke);
        mSignal.countDown();
    }
}

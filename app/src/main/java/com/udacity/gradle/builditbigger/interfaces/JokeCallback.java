package com.udacity.gradle.builditbigger.interfaces;

/**
 * Created by Kevin on 12/23/2015.
 */
public interface JokeCallback {
    void showErrorMessage(String error);
    void launchJokeIntent(String joke);
}

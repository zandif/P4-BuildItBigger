package com.udacity.gradle.jokes;

import java.util.Random;

public class JokeProvider {

    private String[] jokes = {
            "What do you call a fake noodle?\nAn impasta",
            "Watson: Holmes, what kind of rock is this?\nSherlock Holmes: Why that's sedimentary, my " +
                    "dear Watson.",
            "Did you hear about the famous microbiologist who traveled in thirty different " +
                    "countries and learned to speak six languages?  He was a man of many cultures.",
            "Q: What do you call a joke that is based on cobalt, radon, and yttrium?\nA: CoRnY.",
            "What did the receiver say to the radio wave?\nOUCH! That megahertz."
    };

    public String getJoke() {
        Random rand = new Random();
        int jokePosition = rand.nextInt(jokes.length);

        return jokes[jokePosition];
    }
}

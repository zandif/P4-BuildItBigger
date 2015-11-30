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
            "What did the receiver say to the radio wave?\nOUCH! That megahertz.",
            "Helium walks into a bar and orders a beer.  The bartender says \"We don't server " +
                    "noble gases here.\" Helium doesn't react.",
            "A Higgs Boson walks into a church and the priest says \"We don't allow Higgs Bosons " +
                    "in here.\" The Higgs Boson replies, \"But without me how could have mass?\"",
            "Why can't chemists prank their friends?\nThey lack the element of surprise!",
            "Sodium sodium sodium sodium sodium sodium sodium sodium Batman!",
            "Have you heard the one about the sick chemist?\nIf you can't helium, and you can't " +
                    "curium, you'll probably have to barium.",
            "Why can't you trust atoms?\nThey make up everything.",
            "A neutron walks into a bar and asks the bartender how much for a drink.\nThe " +
                    "bartender says \"For you, no charge.\"",
            "What does a subatomic duck say?\nQuark!",
            "What doe physicists enjoy doing the most at sporting events?\nThe wave.",
            "Does a radioactive cat have 18 half-lives?",
            "How many programmers does it take to change a light bulb?\nCan't be done sorry, it's" +
                    " a hardware problem.",
            "When do astronauts eat their lunch?\nAt launch time.",
            "What do you think of that new restaurant on the moon?\nThe food's great but it has " +
                    "no atmosphere.",
            "Why did hydrogen marry carbon?\nBecause they bonded so well."
    };

    public String getJoke() {
        Random rand = new Random();
        int jokePosition = rand.nextInt(jokes.length);

        return jokes[jokePosition];
    }
}

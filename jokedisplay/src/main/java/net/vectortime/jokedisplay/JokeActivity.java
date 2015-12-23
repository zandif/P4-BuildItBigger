package net.vectortime.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.udacity.gradle.jokes.JokeProvider;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke_key";
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        title = getTitle();
    }

    public void refreshJoke(View view) {
        JokeProvider jokeProvider = new JokeProvider();
        String joke = jokeProvider.getJoke();

//        Fragment jokeFragment = new JokeActivityFragment();
//        Bundle args = new Bundle();
//        args.putString(JOKE_KEY, joke);
//        getSupportFragmentManager().popBackStack();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.fragment, jokeFragment)
//                .addToBackStack((String) title)
//                .commit();


        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }
}

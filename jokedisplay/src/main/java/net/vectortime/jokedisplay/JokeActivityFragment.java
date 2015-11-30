package net.vectortime.jokedisplay;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    private Button mJokeButton;
    private TextView mJokeTextView;

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_display, container, false);

        mJokeButton = (Button) view.findViewById(R.id.get_joke_button);
        mJokeTextView = (TextView) view.findViewById(R.id.newJokeTextView);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(JokeActivity.JOKE_KEY)) {
            String joke = intent.getStringExtra(JokeActivity.JOKE_KEY);
            if (joke != null && joke.length() != 0) {
            mJokeTextView.setText(joke);}
            else {
                mJokeTextView.setText(R.string.error_joke_retrieval);
            }
        } else {
            mJokeTextView.setText(R.string.error_joke_retrieval);
        }


        return view;
    }
}

package com.udacity.gradle.builditbigger.async;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.interfaces.JokeCallback;
import com.udacity.gradle.builditbigger.jokesbackend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Kevin on 12/12/2015.
 */
public class AsyncJoke {
    private Context mContext;
    private JokeCallback mJokeCallback;

    public AsyncJoke(Context context){
        mContext = context;
    }

    public void tellJoke(JokeCallback callback) {
        mJokeCallback = callback;
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.execute();
    }

    private class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi mApiService = null;

        public EndpointsAsyncTask() {

        }

        @Override
        protected String doInBackground(Void... params) {
            if(mApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                mApiService = builder.build();
            }

//        context = params[0].first;
//        String name = params[0].second;

            try {
                return mApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
//                Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(mContext, JokeActivity.class);
//                intent.putExtra(JokeActivity.JOKE_KEY, result);
//                mContext.startActivity(intent);
                mJokeCallback.launchJokeIntent(result);
            } else {
//                Toast.makeText(mContext, R.string.default_joke, Toast.LENGTH_LONG).show();
                mJokeCallback.showErrorMessage(mContext.getResources().getString(R.string.default_joke));
            }
        }
    }
}



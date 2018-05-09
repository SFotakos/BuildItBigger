package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

// Implemented as seen on https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
public class JokeBackendAsyncTask extends AsyncTask<JokeBackendAsyncTask.IAPICallback, Void, String> {
    private static String LOCAL_HOST = "http://10.0.2.2:8080";
    private MyApi myApiService = null;
    private IAPICallback callback;

    @Override
    protected String doInBackground(IAPICallback... callbacks) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(LOCAL_HOST + "/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        callback = callbacks[0];
        Log.d("AAA", "Callback");
        try {
            Log.d("AAA", "GETJOKE");
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.d("AAA", e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (callback != null) {
            callback.displayJoke(result);
        }
    }

    public interface IAPICallback {
        void displayJoke(String joke);
    }
}

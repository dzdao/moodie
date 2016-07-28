package com.example.rui.location;



// libraries needed for Giphy Calls
import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler;

import java.io.IOException;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONObject;
//
//import java.io.IOException;


public class Mood
{
    Context context;
    GiphyData giphyData;
    private final Handler handler;



    // constructor
    public Mood(Context context) {

        this.context = context;
        handler = new Handler(context.getMainLooper());
    }

    public void getGiphy() {
        String apiKey = "dc6zaTOxFJmzC";

        String imageUrl = "http://api.giphy.com/v1/gifs/search?q=happy&api_key=dc6zaTOxFJmzC";

        // check for network connection
        if(networkIsActive()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(imageUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Request request, IOException e) {
//                    runOnUiThread(new Runnable() {
//
//                        public void run() {
//                            // toggleRefresh();
//                        }
//                    });
//                }

                @Override
                public void onFailure(Call request, IOException e) {
                    runOnUiThread(new Runnable() {

                        public void run() {
                            // toggleRefresh();
                        }
                    });
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        public void run() {
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            giphyData = getGif(jsonData);
                            Log.v(TAG, "Giphy Gif Data from Response: " + giphyData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            Log.i(TAG, "Response Unsuccessful");
                        }
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception Caught: ", e);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private GiphyData getGif(String jsonData) throws JSONException {
        JSONObject giphy = new JSONObject(jsonData);
        JSONObject data = giphy.getJSONObject("data");


    }
    private boolean networkIsActive() {

        boolean isActive = false;
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = manager.getActiveNetworkInfo();

        if(net != null && net.isAvailable())
            isActive = true;

        return isActive;
    }

    private void runOnUiThread(Runnable r) {
        handler.post(r);
    }
    private void updateDisplay() {

    }
}
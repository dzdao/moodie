package com.example.rui.location;

// libraries needed for Giphy Calls
import android.app.Activity;
import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageButton;
import android.os.Handler;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.*;
import java.util.Random;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import android.widget.TextView;
//import java.io.IOException;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.widget.Button;
//import android.view.View;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.os.Bundle;
//import android.os.AsyncTask;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class Mood
{
    Context context;
    GiphyData[] giphyData;
    private final Handler handler;
    public static final String TAG = Mood.class.getSimpleName();
    public Activity activity;
    String moodToSearchFor;
    String giphyUrl;
    ImageButton imgBtn;

    // constructor
    public Mood(Context context, Activity activity, String mood, ImageButton imgBtn) {

        this.context = context;
        this.activity = activity;
        handler = new Handler(context.getMainLooper());
        this.moodToSearchFor = mood;
        this.giphyUrl = null;
        this.imgBtn = imgBtn;
    }

    public void getGiphy() {

        String apiKey = "dc6zaTOxFJmzC";

        String imageUrl = "http://api.giphy.com/v1/gifs/search?q=" +
                moodToSearchFor + "&api_key=" + apiKey;

        // check for network connection
        if(networkIsActive()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(imageUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Call request, IOException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // toggleRefresh();
                        }
                    });
                }

                @Override
                public void onResponse(Call request, Response response) throws IOException {
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
                                    pickRandomGiphyUrl();
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

    private GiphyData[] getGif(String jsonData) throws JSONException {
        JSONObject giphy = new JSONObject(jsonData);
        JSONArray data = giphy.getJSONArray("data");

        final int NUM_OF_GIFS = data.length();

        GiphyData[] gifs_array = new GiphyData[NUM_OF_GIFS];

        for(int i = 0; i < NUM_OF_GIFS; i++) {
            JSONObject giphyJson = data.getJSONObject(i);
            GiphyData tempGif = new GiphyData();

            JSONObject imagesJson = giphyJson.getJSONObject("images");
            JSONObject originalJson = imagesJson.getJSONObject("original");

            tempGif.setUrl(originalJson.getString("url"));

            gifs_array[i] = tempGif;
        }

        return gifs_array;
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

    private void pickRandomGiphyUrl() {

        GiphyData[] gifs = giphyData;
        Random rand = new Random();

        int randomint= rand.nextInt((int)(System.currentTimeMillis() % gifs.length));


        GiphyData gif = gifs[randomint];
        giphyUrl = gif.getUrl();
        Glide.with(activity).load(giphyUrl).placeholder(R.drawable.placeholder).override(265,210).fitCenter().into(imgBtn);
    }
}
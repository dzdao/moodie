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

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONObject;
//import java.io.IOException;


// glide libraries for gif image loading
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


public class Mood
{
    Context context;
    GiphyData[] giphyData;
    private final Handler handler;
    public static final String TAG = Mood.class.getSimpleName();
    public Activity activity;

    // constructor
    public Mood(Context context, Activity activity) {

        this.context = context;
        this.activity = activity;
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

        // parse JSON returned by Giphy API call request and set image url
//        gif.setUrl(data.getString("images"));
//        Log.i(TAG, "JSON Data, Gif url is: " + gif);

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
    private void updateDisplay() {

//        String gifUrl = giphyData.getUrl();
//        Log.i(TAG, "updatedDisplay GIF url: " + gifUrl);


        GiphyData[] gifs = giphyData;
        int rand = (int)(Math.random() * gifs.length);

        GiphyData gif = gifs[rand];

        String gifUrl = gif.getUrl();

        ImageView image = (ImageView)activity.findViewById(R.id.gifImageView);

        Glide.with(this.activity).load(gifUrl).into(image);

    }
}
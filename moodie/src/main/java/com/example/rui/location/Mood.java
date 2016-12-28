/**
 * Created by Rodrigo Figueroa, David Dao,
 Diana Galvan, and Sara Lipowsky  on 7/27/16.
 */
package com.example.rui.location;


import android.app.Activity;
import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;

import android.graphics.drawable.Drawable;
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

/**************************************************************************
 * This class is responsable of connecting to the giphy API and create
 * GIF images for buttons on the main screen
 ***************************************************************************/
public class Mood
{
    //local variables
    Context context;
    GiphyData[] giphyData;
    private final Handler handler;
    public static final String TAG = Mood.class.getSimpleName();
    public Activity activity;
    String moodToSearchFor;
    String giphyUrl;
    ImageButton imgBtn;


    /*************************************************************************
     *  constructor
     *      Mood
     * input:
     * context : Current state of the application
     * activity :the name of the activity from where it has been called
     * mood: a String parameter to do a search for a gif
     * imgBtn: ImageButton a button where the image is going to be placed on
     *
     *  return: none
     **************************************************************************/
    public Mood(Context context, Activity activity, String mood, ImageButton imgBtn) {

        this.context = context;
        this.activity = activity;
        handler = new Handler(context.getMainLooper());
        this.moodToSearchFor = mood;
        this.giphyUrl = null;
        this.imgBtn = imgBtn;
    }
    /*************************************************************************
     *    public void getGiphy()
     *     connects to giphy API by using a public key and a term to look for a gif.
     *     Also in charge to check for network connection to the giphy server
     *
     * input: none
     *
     *  return: none
     **************************************************************************/
    public void getGiphy() {

        //keyy needed to access API
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

                /*************************************************************************
                 * onFailure(Call request, IOException e)
                 * called by getGiphy to check for a request call that connects to the
                 * parameters
                 *
                 * input :none
                 *
                 *  return: none
                 **************************************************************************/
                @Override
                public void onFailure(Call request, IOException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // toggleRefresh();


                        }
                    });
                }

                /*************************************************************************
                 * onResponse(Call request, Response response)
                 * if conection to the server is granted, a JSON object with multiple
                 *  urls is returned.
                 *
                 * input :none
                 *
                 *  return: none
                 **************************************************************************/
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


    /*************************************************************************
     * getGif(String jsonData)
     *  urls is returned.
     *
     * input :
     * a string in Json format
     * return type GiphyData[]
     * networkIsActive()
     *
     *  return: GiphyData[]
     **************************************************************************/
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

    /*************************************************************************
     *  private boolean networkIsActive()
     *  checks for network connection and it theres no conection set moods to
     *  a internet_access.png to let the user know.
     *
     * input : none
     *
     *  return:
     *     isActive,a boolean variable with teh connection status
     **************************************************************************/
    private boolean networkIsActive() {

        boolean isActive = false;
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = manager.getActiveNetworkInfo();

        //if no conection use image button to tell user
        Glide.with(activity).load(R.drawable.internet_acces).placeholder(R.drawable.placeholder).override(265, 210).fitCenter().into(imgBtn);
        if(net != null && net.isAvailable())
            isActive = true;

        return isActive;
    }

    private void runOnUiThread(Runnable r) {
        handler.post(r);
    }

    /*************************************************************************
     *  pickRandomGiphyUrl()
     *      picks a random url to display from the retuning call from giphy API
     *
     * input : none
     *
     *  return: none
     **************************************************************************/
    private void pickRandomGiphyUrl() {

        GiphyData[] gifs = giphyData;
        int rand = (int)(Math.random() * gifs.length);

        GiphyData gif = gifs[rand];
        giphyUrl = gif.getUrl();
        Glide.with(activity).load(giphyUrl).placeholder(R.drawable.placeholder).override(170, 98).fitCenter().into(imgBtn);
     }


}
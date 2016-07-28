package com.example.rui.location;



// libraries needed for Giphy Calls
import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
//import com.squareup.okhttp.Call;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;

//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;



/**
 * Created by rui on 7/21/16.
 */
public class mood extends Activity
{
//    String name;
//    String imageURL;
//    String apiKey = "dc6zaTOxFJmzC";
//    //name is the mood it self i.e. happy, sad.
//    String giphyUrl = "http://api.giphy.com/v1/gifs/random" +
//            "?api_key=" +
//            apiKey +
//            "&tag=" +
//            "cat";
//    public mood(String name)
//    {
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(giphyUrl)
//                .build();
//    }



    private void getGiphy() {
        String apiKey = "dc6zaTOxFJmzC";

        String imageUrl = "http://api.giphy.com/v1/gifs/search?q=happy&api_key=dc6zaTOxFJmzC";

        if(networkIsActive()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(imageUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override

            });

        }
    }

    private boolean networkIsActive() {

        boolean isActive = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = manager.getActiveNetworkInfo();

        if(net != null && net.isAvailable())
            isActive = true;

        return isActive;
    }

}

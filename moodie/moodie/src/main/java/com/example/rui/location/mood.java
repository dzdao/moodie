package com.example.rui.location;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;

/**
 * Created by rui on 7/21/16.
 */
public class mood
{
    String name;
    String imageURL;
    String apiKey = "dc6zaTOxFJmzC";
    //name is the mood it self i.e. happy, sad.
    String giphyUrl = "http://api.giphy.com/v1/gifs/random" +
            "?api_key=" +
            apiKey +
            "&tag=" +
            "cat";
    public mood(String name)
    {

    }


}

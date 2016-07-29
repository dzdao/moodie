package com.example.rui.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// glide libraries for gif image loading
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity
{
    String searchTermForHappyMood="entertainment";
    String searchTermForSadMood="dessert";
    String searchTermForadventurousMood="food";
    String searchTermForhealthyMood="healthy";

    String happyUrl, sadUrl, healthyUrl, adventurousUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // set Giphy images to buttons
        setContentView(R.layout.activity_main);
        //giphy();
        //ImageView mood1=(ImageView)findViewById(R.id.mood1); // button for happy mood
        //Glide.with(this).load(happyUrl).into(mood1);
    }

    // on click, send request for entertainment to next activity (using xml to call function)
    public void buttonClicked(View v)
    {
        Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
        switch(v.getId())
        {
            case(R.id.mood1):
                toRestaurantResults.putExtra("term", searchTermForHappyMood );
                break;
            case(R.id.mood2):
                toRestaurantResults.putExtra("term", searchTermForSadMood);
                break;
            case(R.id.mood3):
                toRestaurantResults.putExtra("term", searchTermForadventurousMood);
                break;
            default:
                toRestaurantResults.putExtra("term", searchTermForhealthyMood);
                break;
        }
        startActivity(toRestaurantResults);
    }
    public void giphy()
    {

        // create Mood object and pass the context of this activity to the class
        Mood happyMood = new Mood(this, this, "happy");
        Mood sadMood = new Mood(this, this, "sad");
        Mood healthyMood = new Mood(this, this, "healthy");
        Mood adventurousMood = new Mood(this, this, "adventurous");

        happyUrl = happyMood.getGiphy();
        //happyUrl = happyMood.getUrl();
        //sadUrl = sadMood.getGiphy();
        //healthyUrl = healthyMood.getGiphy();
        //adventurousUrl = adventurousMood.getGiphy();
//
//        TextView text = (TextView)findViewById(R.id.textBox);
//
//
//        if(happyUrl != null)
//            text.append(happyUrl);
//        else
//            text.append("Failed...");

    }

}

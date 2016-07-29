package com.example.rui.location;

//import android.content.Context;
//import android.location.Criteria;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.net.Uri;

//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.yelp.clientlib.connection.YelpAPI;
//import com.yelp.clientlib.connection.YelpAPIFactory;
//import com.yelp.clientlib.entities.Business;
//import com.yelp.clientlib.entities.SearchResponse;
//import com.yelp.clientlib.entities.options.BoundingBoxOptions;
//import com.yelp.clientlib.entities.options.CoordinateOptions;
//
//import java.security.Provider;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.widget.ImageButton;
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
    String searchTermForadventurousMood="hot and new";
    String searchTermForhealthyMood="healthy";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final ImageButton mood1=(ImageButton) findViewById(R.id.mood1); // button for happy mood
        final ImageButton mood2=(ImageButton) findViewById(R.id.mood2); // button for sad mood
        final ImageButton mood3=(ImageButton) findViewById(R.id.mood3); // button for adventurous mood
        final ImageButton mood4=(ImageButton) findViewById(R.id.mood4); // button for healthy mood


        Mood happy = new Mood(this, this, "happy", mood1);
        happy.getGiphy();
        Mood sad = new Mood(this, this, "sad", mood2);
        sad.getGiphy();
        Mood adventurous = new Mood(this, this, "adventurous", mood3);
        adventurous.getGiphy();
        Mood healthy = new Mood(this, this, "healthy", mood4);
        healthy.getGiphy();


    }
    // on click, send request for a term to next activity (using xml to call function)
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
}

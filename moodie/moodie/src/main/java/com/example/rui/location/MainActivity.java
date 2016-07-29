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
    String happyUrl, sadUrl, healthyUrl, adventurousUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.test);

//        final ImageButton mood1=(ImageButton) findViewById(R.id.mood1); // button for happy mood
//        final ImageButton mood2=(ImageButton) findViewById(R.id.mood2); // button for sad mood
//        final ImageButton mood3=(ImageButton) findViewById(R.id.mood3); // button for adventurous mood
//        final ImageButton mood4=(ImageButton) findViewById(R.id.mood4); // button for healthy mood
//
//        // set Giphy images to buttons
        giphy();
//
//        Glide.with(this).load(happyUrl).into(mood1);
//        Glide.with(this).load(sadUrl).into(mood2);
//        Glide.with(this).load(healthyUrl).into(mood3);
//        Glide.with(this).load(adventurousUrl).into(mood4);

//        // on click send request for entertainment to next activity
//        mood1.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            { Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
//                toRestaurantResults.putExtra("term", "entertainment");
//                startActivity(toRestaurantResults);
//                return false;
//            }
//
//        });
//
//        // on click send request for dessert to next activity
//        mood2.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            { Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
//                toRestaurantResults.putExtra("term", "dessert");
//                startActivity(toRestaurantResults);
//                return false;
//            }
//
//        });
//
//        // on click send request for hot and new to next activity
//        mood3.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            { Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
//                toRestaurantResults.putExtra("term", "hot and new");
//                startActivity(toRestaurantResults);
//                return false;
//            }
//        });
//
//        // on click send request for healthy to next activity
//        mood4.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            { Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
//                toRestaurantResults.putExtra("term", "healthy");
//                startActivity(toRestaurantResults);
//                return false;
//            }
//        });
    }

    public void giphy() {

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

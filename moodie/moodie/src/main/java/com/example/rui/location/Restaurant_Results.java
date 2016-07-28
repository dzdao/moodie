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


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

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

public class Restaurant_Results extends AppCompatActivity
{
    String location = "you're @ ";
    double lat = 0.0;
    double lon = 0.0;

    // default parameters
    String term = "hot and new"; //used to always look for food places
    String numberOfResults = "5"; //limit the number of results to 10 businesses
    String category_filter = "food";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    int search_category;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__results);
        Bundle getTerm = getIntent().getExtras();
        this.term=getTerm.getString("term");
        getCoordinates();
        //updateCoordinates();
         String con_test = "";

        yelp();
    }


    private void updateCoordinates() {
        TextView text = (TextView) findViewById(R.id.textLocation);
        String currentLocation = location + "Latitude: " + lat + " Longitude: " + lon;
        text.setText(currentLocation);

    }


    private void getCoordinates() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (loc == null) {
            loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc == null) {
                lon = 100;
                lat = 100;
            }
        }
        lon = loc.getLongitude();
        lat = loc.getLatitude();
    }

    private void yelp()
    {
        String consumerKey ="GH0hCC83JR1G-T_7T54jxw";
        String consumerSecret="Dw-cj6EtFAIRpu9pzSRjEuHEUNs";
        String token="VPvzcqEfLh07yrHaAzIARouynBWnDjxv";
        String tokenSecret="QYcm0Coq4XRfjLTSfyCv4Zlb38c";
        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey,consumerSecret , token,tokenSecret );
        YelpAPI yelpAPI = apiFactory.createAPI();

        Map<String, String> params = new HashMap<>();
        // general params
        params.put("term", term);
        params.put("limit", numberOfResults);
        params.put("category_filter", category_filter);

        // build a coordinate object for the Yelp API to understand
        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(lat)
                .longitude(lon).build();

        // call request to API
        Call<SearchResponse> call = yelpAPI.search(coordinate, params);

        // setup for asynchronous request
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {

            //TextView con = (TextView) findViewById(R.id.resultsView);
            String con_test = "";
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response)
            {   ImageView img = (ImageView) findViewById(R.id.image);
                ImageView img2 = (ImageView) findViewById(R.id.image2);
                ImageView img3 = (ImageView) findViewById(R.id.image3);
                ImageView img4 = (ImageView) findViewById(R.id.image4);
                ImageView img5 = (ImageView) findViewById(R.id.image5);


                SearchResponse searchResponse = response.body();
                ArrayList<Business> businesses = searchResponse.businesses();
                for (int i = 0; i < Integer.parseInt(numberOfResults); i++)
                {
                    Restaurant restaurant = new Restaurant(businesses, i);


                    Picasso.with(getApplicationContext()).load(businesses.get(0).imageUrl()).into(img);
                    Picasso.with(getApplicationContext()).load(businesses.get(1).imageUrl()).into(img2);
                    Picasso.with(getApplicationContext()).load(businesses.get(2).imageUrl()).into(img3);
                    Picasso.with(getApplicationContext()).load(businesses.get(3).imageUrl()).into(img4);
                    Picasso.with(getApplicationContext()).load(businesses.get(4).imageUrl()).into(img5);

                }
                img.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {Intent toRestaurantResults = new Intent(Restaurant_Results.this, RestaurantDetails.class);
                        startActivity(toRestaurantResults);
                        return false;
                    }
                });

            }

            @Override
            public void onFailure (Call<SearchResponse> call, Throwable t)
            {
                // HTTP error happened, do something to handle it.
                con_test = "fail";
            }

        };

        // make the asynchronous request
        call.enqueue(callback);
    }

}

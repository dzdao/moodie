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
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
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


    String cityLocation;
    double lat = 33.873825;
    double lon = -117.924372;

    // default parameters
    String term = ""; //used to always look for food places
    String numberOfResults = "8"; //limit the number of results to 10 businesses
    String category_filter = "food";

    Restaurant[] restaurant;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    int search_category;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        restaurant = new Restaurant[Integer.parseInt(numberOfResults)];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__results);

        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();
        this.term=getTerm.getString("term");
        this.cityLocation=getTerm.getString("cityLocation");
        Intent toRestaurantResults = new Intent(Restaurant_Results.this, RestaurantDetails.class);
        toRestaurantResults.putExtra("term", term);
        toRestaurantResults.putExtra("cityLocation", cityLocation);


        String con_test = "";
        yelp();

    }



    private void getCoordinates() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        //REDUNDANT setting a defualt location here? why dont we use a default from default
       // if (loc != null) {

            // getLastKnownLocation returns the most recent location request
            lat = loc.getLatitude();
            lon = loc.getLongitude();
       // }
       // else {
            // getLastKnownLocation did not find a recent location request
            // prompt the OS for a new location or set a default location
            // lm.requestLocationUpdates();

        //    lat = 33.873825;
           // lon = -117.924372;
       // }
    }

    private void yelp() {
        String consumerKey = "GH0hCC83JR1G-T_7T54jxw";
        String consumerSecret = "Dw-cj6EtFAIRpu9pzSRjEuHEUNs";
        String token = "VPvzcqEfLh07yrHaAzIARouynBWnDjxv";
        String tokenSecret = "QYcm0Coq4XRfjLTSfyCv4Zlb38c";
        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();
        // call request to API
        Call<SearchResponse> call;

        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", term);
        params.put("limit", numberOfResults);
        params.put("category_filter", category_filter);


        if(cityLocation != "") {
            // if the user entered a city location, use that for the search
            call = yelpAPI.search(cityLocation, params);
        }
        else {

            // user did not enter a city location, so use their GPS
            getCoordinates();

            // build a coordinate object for the Yelp API to understand
            CoordinateOptions coordinate = CoordinateOptions.builder()
                    .latitude(lat)
                    .longitude(lon).build();
            call = yelpAPI.search(coordinate, params);
        }

        // setup for asynchronous request
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {

            //TextView con = (TextView) findViewById(R.id.resultsView);
            String con_test = "";

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                ImageView img = (ImageView) findViewById(R.id.image);
                ImageView img2 = (ImageView) findViewById(R.id.image2);
                ImageView img3 = (ImageView) findViewById(R.id.image3);
                ImageView img4 = (ImageView) findViewById(R.id.image4);
                ImageView img5 = (ImageView) findViewById(R.id.image5);
                ImageView img6 = (ImageView) findViewById(R.id.image6);
                ImageView img7 = (ImageView) findViewById(R.id.image7);
                ImageView img8 = (ImageView) findViewById(R.id.image8);




                SearchResponse searchResponse = response.body();
                final ArrayList<Business> businesses = searchResponse.businesses();

                for (int i = 0; i < Integer.parseInt(numberOfResults); i++)
                {
                    String address;
                    double dist;
                    try {
                        address =businesses.get(i).location().address().get(i) ;
                        dist = businesses.get(i).distance();
                    }
                    catch (Exception e){
                        address = "no address available";
                        dist = 0;
                    }
                    restaurant[i] = new Restaurant(businesses.get(i).name(), businesses.get(i).phone(),
                            address, dist, businesses.get(i).snippetText(),
                            businesses.get(i).imageUrl(),businesses.get(i).location().city(),businesses.get(i).location().stateCode());
                }

                Picasso.with(getApplicationContext()).load(restaurant[0].getImageURL()).resize(250, 250).centerInside().into(img);
                Picasso.with(getApplicationContext()).load(restaurant[1].getImageURL()).resize(250,250).centerInside().into(img2);
                Picasso.with(getApplicationContext()).load(restaurant[2].getImageURL()).resize(250,250).centerInside().into(img3);
                Picasso.with(getApplicationContext()).load(restaurant[3].getImageURL()).resize(250,250).centerInside().into(img4);
                Picasso.with(getApplicationContext()).load(restaurant[4].getImageURL()).resize(250,250).centerInside().into(img5);
                Picasso.with(getApplicationContext()).load(restaurant[5].getImageURL()).resize(250,250).centerInside().into(img6);
                Picasso.with(getApplicationContext()).load(restaurant[6].getImageURL()).resize(250,250).centerInside().into(img7);
                Picasso.with(getApplicationContext()).load(restaurant[7].getImageURL()).resize(250,250).centerInside().into(img8);

                //Picasso.with(getApplicationContext()).load(businesses.get(2).imageUrl()).resize(250,250).centerInside().into(img3);
                //Picasso.with(getApplicationContext()).load(businesses.get(3).imageUrl()).resize(250,250).centerInside().into(img4);
                //Picasso.with(getApplicationContext()).load(businesses.get(4).imageUrl()).resize(250,250).centerInside().into(img5);
                //Picasso.with(getApplicationContext()).load(businesses.get(5).imageUrl()).resize(250,250).centerInside().into(img6);
                //Picasso.with(getApplicationContext()).load(businesses.get(6).imageUrl()).resize(250,250).centerInside().into(img7);
                //Picasso.with(getApplicationContext()).load(businesses.get(7).imageUrl()).resize(250,250).centerInside().into(img8);
                final String finalBusinessAddress = businessAddress;
                final double finalDistance = distance;

                img.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);

                        toRestaurantDetails.putExtra("name", businessName);
                        toRestaurantDetails.putExtra("address", finalBusinessAddress);
                        toRestaurantDetails.putExtra("phoneNumber", businessPhoneNumber);
                        toRestaurantDetails.putExtra("imageURL", imageURL);
                        toRestaurantDetails.putExtra("city", city);
                        toRestaurantDetails.putExtra("state", state);
                        toRestaurantDetails.putExtra("reviewSnippet", reviewSnippet);
                        toRestaurantDetails.putExtra("distance", finalDistance);
                        toRestaurantDetails.putExtra("term", term);
                        startActivity(toRestaurantDetails);
                        return false;
                    }
                });

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
                TextView connectionTest = (TextView) findViewById(R.id.test);
                con_test = "No conection";
                connectionTest.setText(con_test);
            }

        };

        // make the asynchronous request
           call.enqueue(callback);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

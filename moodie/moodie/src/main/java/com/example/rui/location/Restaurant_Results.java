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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    String numberOfResults = "10"; //limit the number of results to 10 businesses
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


        if(cityLocation != null && !cityLocation.isEmpty()) {
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
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response)
            {
                restaurant = new Restaurant[Integer.parseInt(numberOfResults)];

                ImageView img = (ImageView) findViewById(R.id.image);
                ImageView img2 = (ImageView) findViewById(R.id.image2);
                ImageView img3 = (ImageView) findViewById(R.id.image3);
                ImageView img4 = (ImageView) findViewById(R.id.image4);
                ImageView img5 = (ImageView) findViewById(R.id.image5);
                ImageView img6 = (ImageView) findViewById(R.id.image6);
                ImageView img7 = (ImageView) findViewById(R.id.image7);
                ImageView img8 = (ImageView) findViewById(R.id.image8);
                ImageView img9 = (ImageView) findViewById(R.id.image9);
                ImageView img10 = (ImageView) findViewById(R.id.image10);
                ImageView img11 = (ImageView) findViewById(R.id.image11);
                ImageView img12 = (ImageView) findViewById(R.id.image12);
                ImageView img13 = (ImageView) findViewById(R.id.image13);
                ImageView img14 = (ImageView) findViewById(R.id.image14);
                ImageView img15 = (ImageView) findViewById(R.id.image15);
                ImageView img16 = (ImageView) findViewById(R.id.image16);
                ImageView img17 = (ImageView) findViewById(R.id.image17);
                ImageView img18 = (ImageView) findViewById(R.id.image18);
                ImageView img19 = (ImageView) findViewById(R.id.image19);
                ImageView img20 = (ImageView) findViewById(R.id.image20);


                SearchResponse searchResponse = response.body();
                final ArrayList<Business> businesses = searchResponse.businesses();

                for (int i = 0; i < Integer.parseInt(numberOfResults); i++) {
                    String address;
                    double dist;
                    try {
                        address = businesses.get(i).location().address().get(i);
                        dist = businesses.get(i).distance();
                    } catch (Exception e) {
                        address = "no address available";
                        dist = 0;
                    }
                    restaurant[i] = new Restaurant(businesses.get(i).name(), businesses.get(i).phone(),
                            address, dist, businesses.get(i).snippetText(),
                            businesses.get(i).imageUrl(), businesses.get(i).location().city(), businesses.get(i).location().stateCode());
                }
                //Glide.with(Restaurant_Results.this).load(restaurant[0].getImageURL()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(img);
                Picasso.with(getApplicationContext()).load(restaurant[0].getImageURL()).resize(250, 250).centerInside().into(img);
                Picasso.with(getApplicationContext()).load(restaurant[1].getImageURL()).resize(250, 250).centerInside().into(img2);
                Picasso.with(getApplicationContext()).load(restaurant[2].getImageURL()).resize(250, 250).centerInside().into(img3);
                Picasso.with(getApplicationContext()).load(restaurant[3].getImageURL()).resize(250, 250).centerInside().into(img4);
                Picasso.with(getApplicationContext()).load(restaurant[4].getImageURL()).resize(250, 250).centerInside().into(img5);
                Picasso.with(getApplicationContext()).load(restaurant[5].getImageURL()).resize(250, 250).centerInside().into(img6);
                Picasso.with(getApplicationContext()).load(restaurant[6].getImageURL()).resize(250, 250).centerInside().into(img7);
                Picasso.with(getApplicationContext()).load(restaurant[7].getImageURL()).resize(250, 250).centerInside().into(img8);
                Picasso.with(getApplicationContext()).load(restaurant[8].getImageURL()).resize(250, 250).centerInside().into(img9);
                Picasso.with(getApplicationContext()).load(restaurant[9].getImageURL()).resize(250, 250).centerInside().into(img10);
              //  Picasso.with(getApplicationContext()).load(restaurant[10].getImageURL()).resize(250, 250).centerInside().into(img11);
               // Picasso.with(getApplicationContext()).load(restaurant[11].getImageURL()).resize(250, 250).centerInside().into(img12);
               // Picasso.with(getApplicationContext()).load(restaurant[12].getImageURL()).resize(250, 250).centerInside().into(img13);
               // Picasso.with(getApplicationContext()).load(restaurant[13].getImageURL()).resize(250, 250).centerInside().into(img14);
               // Picasso.with(getApplicationContext()).load(restaurant[14].getImageURL()).resize(250, 250).centerInside().into(img15);
               // Picasso.with(getApplicationContext()).load(restaurant[15].getImageURL()).resize(250, 250).centerInside().into(img16);
               // Picasso.with(getApplicationContext()).load(restaurant[16].getImageURL()).resize(250, 250).centerInside().into(img17);
               // Picasso.with(getApplicationContext()).load(restaurant[17].getImageURL()).resize(250, 250).centerInside().into(img18);
               // Picasso.with(getApplicationContext()).load(restaurant[18].getImageURL()).resize(250, 250).centerInside().into(img19);
                //Picasso.with(getApplicationContext()).load(restaurant[19].getImageURL()).resize(250, 250).centerInside().into(img20);


                img.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);
                        toRestaurantDetails.putExtra("name", restaurant[0].getName());
                        toRestaurantDetails.putExtra("address", restaurant[0].getAddress());
                        toRestaurantDetails.putExtra("phoneNumber", restaurant[0].getPhoneNumber());
                        toRestaurantDetails.putExtra("imageURL", restaurant[0].getImageURL());
                        toRestaurantDetails.putExtra("city", restaurant[0].getCity());
                        toRestaurantDetails.putExtra("state", restaurant[0].getState());
                        toRestaurantDetails.putExtra("reviewSnippet", restaurant[0].getReviewSnippet());
                        toRestaurantDetails.putExtra("distance", restaurant[0].getDistance());
                        toRestaurantDetails.putExtra("term", term);
                        startActivity(toRestaurantDetails);

                    }
                });
                img2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    { Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);
                        toRestaurantDetails.putExtra("name", restaurant[1].getName());
                        toRestaurantDetails.putExtra("address", restaurant[1].getAddress());
                        toRestaurantDetails.putExtra("phoneNumber", restaurant[1].getPhoneNumber());
                        toRestaurantDetails.putExtra("imageURL", restaurant[1].getImageURL());
                        toRestaurantDetails.putExtra("city", restaurant[1].getCity());
                        toRestaurantDetails.putExtra("state", restaurant[1].getState());
                        toRestaurantDetails.putExtra("reviewSnippet", restaurant[1].getReviewSnippet());
                        toRestaurantDetails.putExtra("distance", restaurant[1].getDistance());
                        toRestaurantDetails.putExtra("term", term);       startActivity(toRestaurantDetails);
                        startActivity(toRestaurantDetails);
                    }
                });

                img3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    { Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);
                        toRestaurantDetails.putExtra("name", restaurant[2].getName());
                        toRestaurantDetails.putExtra("address", restaurant[2].getAddress());
                        toRestaurantDetails.putExtra("phoneNumber", restaurant[2].getPhoneNumber());
                        toRestaurantDetails.putExtra("imageURL", restaurant[2].getImageURL());
                        toRestaurantDetails.putExtra("city", restaurant[2].getCity());
                        toRestaurantDetails.putExtra("state", restaurant[2].getState());
                        toRestaurantDetails.putExtra("reviewSnippet", restaurant[2].getReviewSnippet());
                        toRestaurantDetails.putExtra("distance", restaurant[2].getDistance());
                        toRestaurantDetails.putExtra("term", term);       startActivity(toRestaurantDetails);
                        startActivity(toRestaurantDetails);
                    }
                });
                img4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    { Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);
                        toRestaurantDetails.putExtra("name", restaurant[3].getName());
                        toRestaurantDetails.putExtra("address", restaurant[3].getAddress());
                        toRestaurantDetails.putExtra("phoneNumber", restaurant[3].getPhoneNumber());
                        toRestaurantDetails.putExtra("imageURL", restaurant[3].getImageURL());
                        toRestaurantDetails.putExtra("city", restaurant[3].getCity());
                        toRestaurantDetails.putExtra("state", restaurant[3].getState());
                        toRestaurantDetails.putExtra("reviewSnippet", restaurant[3].getReviewSnippet());
                        toRestaurantDetails.putExtra("distance", restaurant[3].getDistance());
                        toRestaurantDetails.putExtra("term", term);       startActivity(toRestaurantDetails);

                        //more efective?
                        toRestaurantDetails.putExtra("restaurant", restaurant[3].toString());
                        startActivity(toRestaurantDetails);
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

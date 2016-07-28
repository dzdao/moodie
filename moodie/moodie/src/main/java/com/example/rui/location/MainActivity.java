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
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Spinner;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.view.View;
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




public class MainActivity extends AppCompatActivity
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
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //getCoordinates();
        //updateCoordinates();
        //yelp();
        giphy();

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
            TextView con = (TextView) findViewById(R.id.resultView);
            ImageView img = (ImageView) findViewById(R.id.image);
            ImageView img2 = (ImageView) findViewById(R.id.image2);
            ImageView img3 = (ImageView) findViewById(R.id.image3);
            ImageView img4 = (ImageView) findViewById(R.id.image4);
            ImageView img5 = (ImageView) findViewById(R.id.image5);
            String gifURL="http://giphy.com/gifs/christian-bale-american-psycho-christiam-9cWlkwWUTgpOg";
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response)
            {
                SearchResponse searchResponse = response.body();
                ArrayList<Business> businesses = searchResponse.businesses();
                for (int i = 0; i < Integer.parseInt(numberOfResults); i++)
                {
                    Restaurant restaurant = new Restaurant(businesses, i);

                    con_test += restaurant.toString()+'\n'+'\n';

                    con.setText(con_test);
                    Picasso.with(getApplicationContext()).load(restaurant.getImageURL()).into(img);
                    Picasso.with(getApplicationContext()).load(gifURL).into(img2);
                }
            }

                @Override
                public void onFailure (Call<SearchResponse> call, Throwable t)
                {
                    // HTTP error happened, do something to handle it.
                    con_test = "fail";
                    con.setText(con_test);
                }

            };

        // make the asynchronous request
        call.enqueue(callback);
    }

    private void giphy() {

        // create Mood object and pass the context of this activity to the class
        Mood myMood = new Mood(this, this);

        myMood.getGiphy();


    }

}

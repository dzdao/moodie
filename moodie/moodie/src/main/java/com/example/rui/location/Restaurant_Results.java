package com.example.rui.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Restaurant_Results extends AppCompatActivity {
    private double lat = 0.0;
    private double lon = 0.0;

    // default parameters
    String term = "pizza"; //used to always look for food places
    String numberOfResults = "5"; //limit the number of results to 10 businesses
    String category_filter = "food";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__results);

        getCoordinates();
        yelp();

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
                    Picasso.with(getApplicationContext()).load(businesses.get(0).imageUrl()).into(img);
                    Picasso.with(getApplicationContext()).load(businesses.get(1).imageUrl()).into(img2);
                    Picasso.with(getApplicationContext()).load(businesses.get(2).imageUrl()).into(img3);
                    Picasso.with(getApplicationContext()).load(businesses.get(3).imageUrl()).into(img4);
                    Picasso.with(getApplicationContext()).load(restaurant.getImageURL()).into(img5);
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

}

package com.example.rui.location;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BoundingBoxOptions;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.security.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ResultsActivity extends AppCompatActivity {

    String location = "you're @ ";
    double lat = 0.0;
    double lon = 0.0;

    // default parameters
    String term = "hot and new"; //used to always look for food places
    String numberOfResults = "10"; //limit the number of results to 10 businesses
    String category_filter = "food";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getCoordinates();
        //updateCoordinates();
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

    private void yelp() {
        YelpAPIFactory apiFactory = new YelpAPIFactory("GH0hCC83JR1G-T_7T54jxw", "Dw-cj6EtFAIRpu9pzSRjEuHEUNs", "LFzLyFa_z8Id4q9TvwfFgE-StpfbU4LN", "5lNteBpwcpLj8vBCJprPsnXEbL8");
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

            TextView con = (TextView) findViewById(R.id.resultsView);
            String con_test = "not goin";

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();

                // create an array of business objects
                ArrayList<Business> businesses = searchResponse.businesses();

                con_test = "CONNECTED!!!!!!" + '\n' + '\n';

                int totalNumberOfResults = Integer.parseInt(numberOfResults);
                int i = 0;

                // iterate through the array and pull the necessary information
                while ( i < totalNumberOfResults) {

                    String businessName = businesses.get(i).name();
                    String address = null;
                    try {
                        address = businesses.get(i).location().address().get(0);
                    }
                    catch (Exception e){
                        address = "no address available";
                    }

                    String phoneNumber = businesses.get(i).displayPhone();
                    Double distance = businesses.get(i).distance();

                    // convert meters to miles
                    distance = distance / 1609.34;

                    String reviewSnippet = businesses.get(i).snippetText();

                    String imageURL = businesses.get(i).imageUrl();

                    // append the details of this particular place to the result String
                    con_test = con_test + (i+1) + ". " + businessName + '\n'
                                        + "Address: " + address + '\n'
                                        + phoneNumber + '\n'
                                        + distance +  " miles away" + '\n'
                                        + "Img url: " + imageURL + '\n' + '\n'
                                        + '"' + reviewSnippet + '"' + '\n' + '\n';
                    i++;
                }


                // Update UI text with the searchResponse.
//                con_test = "CONNECTED!!!!!!                     " + '\n'
//                        + searchResponse.toString();

                con.setText(con_test);

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
                con_test = "fail";
                con.setText(con_test);
            }

        };

        // make the asynchronous request
        call.enqueue(callback);
    }

}
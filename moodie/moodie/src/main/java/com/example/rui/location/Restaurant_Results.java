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
    String term = ""; //used to always look for food places
    String numberOfResults = "8"; //limit the number of results to 10 businesses
    String category_filter = "food";

    Restaurant restaurant;
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

        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();
        this.term=getTerm.getString("term");

        Intent toRestaurantResults = new Intent(Restaurant_Results.this, RestaurantDetails.class);
        //toRestaurantResults.putExtra("response", yelpsearchResponse);
        getCoordinates();

        String con_test = "";

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
        Callback<SearchResponse> callback = new Callback<SearchResponse>()
        {

            //TextView con = (TextView) findViewById(R.id.resultsView);
            String con_test = "";

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response)
            {   ImageView img = (ImageView) findViewById(R.id.image);
                ImageView img2 = (ImageView) findViewById(R.id.image2);
                ImageView img3 = (ImageView) findViewById(R.id.image3);
                ImageView img4 = (ImageView) findViewById(R.id.image4);
                ImageView img5 = (ImageView) findViewById(R.id.image5);
                ImageView img6 = (ImageView) findViewById(R.id.image6);
                ImageView img7 = (ImageView) findViewById(R.id.image7);
                ImageView img8 = (ImageView) findViewById(R.id.image8);


                //res=response.body().toString();

                SearchResponse searchResponse = response.body();
                final ArrayList<Business> businesses = searchResponse.businesses();
                //TextView text = (TextView) findViewById(R.id.resultView);


                //pass results to next activity
                final String businessName =  businesses.get(0).name();
                String businessAddress;
                try
                {
                    businessAddress = businesses.get(0).location().address().get(0);
                }
                catch (Exception e){
                    businessAddress = "no address available";
                }

                final String businessPhoneNumber = businesses.get(0).displayPhone();
                double distance = businesses.get(0).distance();
                final String city = businesses.get(0).location().city();
                final String state = businesses.get(0).location().stateCode();
                final String reviewSnippet = businesses.get(0).snippetText();
                final String imageURL =businesses.get(0).imageUrl();
                // convert meters to miles
                distance = distance / 1609.34;


               // text.setText(res);
                for (int i = 0; i < Integer.parseInt(numberOfResults); i++)
                {
                    Picasso.with(getApplicationContext()).load(businesses.get(0).imageUrl()).into(img);
                    Picasso.with(getApplicationContext()).load(businesses.get(1).imageUrl()).into(img2);
                    Picasso.with(getApplicationContext()).load(businesses.get(2).imageUrl()).into(img3);
                    Picasso.with(getApplicationContext()).load(businesses.get(3).imageUrl()).into(img4);
                    Picasso.with(getApplicationContext()).load(businesses.get(4).imageUrl()).into(img5);
                    Picasso.with(getApplicationContext()).load(businesses.get(5).imageUrl()).into(img6);
                    Picasso.with(getApplicationContext()).load(businesses.get(6).imageUrl()).into(img7);
                    Picasso.with(getApplicationContext()).load(businesses.get(7).imageUrl()).into(img8);
                }
                final String finalBusinessAddress = businessAddress;
                final double finalDistance = distance;
                img.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event)
                    {Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);

                        toRestaurantDetails.putExtra("name",businessName);
                        toRestaurantDetails.putExtra("address", finalBusinessAddress);
                        toRestaurantDetails.putExtra("phoneNumber",businessPhoneNumber);
                        toRestaurantDetails.putExtra("imageURL", imageURL);
                        toRestaurantDetails.putExtra("city",city);
                        toRestaurantDetails.putExtra("state",state);
                        toRestaurantDetails.putExtra("reviewSnippet",reviewSnippet);
                        toRestaurantDetails.putExtra("distance",finalDistance);
                        toRestaurantDetails.putExtra("term",term);
                        startActivity(toRestaurantDetails);
                        return false;
                    }
                });

            }

            @Override
            public void onFailure (Call<SearchResponse> call, Throwable t)
            {
                // HTTP error happened, do something to handle it.
                con_test = "No conection";
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

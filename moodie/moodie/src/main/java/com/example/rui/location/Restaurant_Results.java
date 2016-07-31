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
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Restaurant_Results extends AppCompatActivity {
    String location = "you're @ ";


    private String cityLocation;
    private double lat = 33.873825;
    private double lon = -117.924372;

    // default parameters
    private String term = ""; //used to always look for food places
    private String numberOfResults = "20"; //limit the number of results to 20 businesses
    private String category_filter = "food"; //category to look for
    private String radius_filter="40000"; //max radius filter (25 miles)
    private String sort="1"; //default sort for closest first



    Restaurant[] restaurant;

    static int index = 0;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    int search_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        restaurant = new Restaurant[Integer.parseInt(numberOfResults)];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__results);

        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();
        this.term = getTerm.getString("term");
        this.cityLocation = getTerm.getString("cityLocation");
        Intent toRestaurantResults = new Intent(Restaurant_Results.this, RestaurantDetails.class);
        toRestaurantResults.putExtra("term", term);
        toRestaurantResults.putExtra("cityLocation", cityLocation);


        String con_test = "";
        yelp();

    }


    private void getCoordinates() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        // debugging: check for null prevents emulator from crashing
        if (loc != null) {

            // getLastKnownLocation returns the most recent location request
            lat = loc.getLatitude();
            lon = loc.getLongitude();
        }
        else {
            // getLastKnownLocation did not find a recent location request
            // prompt the OS for a new location with lm.requestLocationUpdates()

            // or use default location
            lat = 33.873825;
            lon = -117.924372;
        }
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
        params.put("radius_filter", radius_filter);


        if (cityLocation != null && !cityLocation.isEmpty()) {
            // if the user entered a city location, use that for the search
            // sort by distance, only supported by giving coordiantes. sort will be done by best matched
            sort ="0";
            params.put("sort", sort);
            call = yelpAPI.search(cityLocation, params);
        } else
        {
            params.put("sort", sort);//sorted by distance
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
                restaurant = new Restaurant[Integer.parseInt(numberOfResults)];

                ArrayList<ImageView> images = new ArrayList<>();

                images.add((ImageView) findViewById(R.id.image));
                images.add((ImageView) findViewById(R.id.image2));
                images.add((ImageView) findViewById(R.id.image3));
                images.add((ImageView) findViewById(R.id.image4));
                images.add((ImageView) findViewById(R.id.image5));
                images.add((ImageView) findViewById(R.id.image6));
                images.add((ImageView) findViewById(R.id.image7));
                images.add((ImageView) findViewById(R.id.image8));
                images.add((ImageView) findViewById(R.id.image9));
                images.add((ImageView) findViewById(R.id.image10));
                images.add((ImageView) findViewById(R.id.image11));
                images.add((ImageView) findViewById(R.id.image12));
                images.add((ImageView) findViewById(R.id.image13));
                images.add((ImageView) findViewById(R.id.image14));
                images.add((ImageView) findViewById(R.id.image15));
                images.add((ImageView) findViewById(R.id.image16));
                images.add((ImageView) findViewById(R.id.image17));
                images.add((ImageView) findViewById(R.id.image18));
                images.add((ImageView) findViewById(R.id.image19));
                images.add((ImageView) findViewById(R.id.image20));


                final ImageView img = (ImageView) findViewById(R.id.image);
                final ImageView img2 = (ImageView) findViewById(R.id.image2);
                final ImageView img3 = (ImageView) findViewById(R.id.image3);
                final ImageView img4 = (ImageView) findViewById(R.id.image4);
                final ImageView img5 = (ImageView) findViewById(R.id.image5);
                final ImageView img6 = (ImageView) findViewById(R.id.image6);
                final ImageView img7 = (ImageView) findViewById(R.id.image7);
                final ImageView img8 = (ImageView) findViewById(R.id.image8);
                final ImageView img9 = (ImageView) findViewById(R.id.image9);
                final ImageView img10 = (ImageView) findViewById(R.id.image10);
                final ImageView img11 = (ImageView) findViewById(R.id.image11);
                final ImageView img12 = (ImageView) findViewById(R.id.image12);
                final ImageView img13 = (ImageView) findViewById(R.id.image13);
                final ImageView img14 = (ImageView) findViewById(R.id.image14);
                final ImageView img15 = (ImageView) findViewById(R.id.image15);
                final ImageView img16 = (ImageView) findViewById(R.id.image16);
                final ImageView img17 = (ImageView) findViewById(R.id.image17);
                final ImageView img18 = (ImageView) findViewById(R.id.image18);
                final ImageView img19 = (ImageView) findViewById(R.id.image19);
                final ImageView img20 = (ImageView) findViewById(R.id.image20);


                SearchResponse searchResponse = response.body();
                final ArrayList<Business> businesses = searchResponse.businesses();

                for (int i = 0; i < Integer.parseInt(numberOfResults); i++) {
                    String address;
                    double dist;
                    try {
                        address = businesses.get(i).location().address().get(0);
                        dist = businesses.get(i).distance();
                    } catch (Exception e) {
                        address = "No address available";
                        dist = 0;
                    }
                    restaurant[i] = new Restaurant(businesses.get(i).name(), businesses.get(i).phone(),
                            address, dist, businesses.get(i).snippetText(),
                            businesses.get(i).imageUrl(), businesses.get(i).location().city(),
                            businesses.get(i).location().stateCode(),
                            businesses.get(i).ratingImgUrlLarge(),
                            businesses.get(i).location().postalCode());
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
                Picasso.with(getApplicationContext()).load(restaurant[10].getImageURL()).resize(250, 250).centerInside().into(img11);
                Picasso.with(getApplicationContext()).load(restaurant[11].getImageURL()).resize(250, 250).centerInside().into(img12);
                Picasso.with(getApplicationContext()).load(restaurant[12].getImageURL()).resize(250, 250).centerInside().into(img13);
                Picasso.with(getApplicationContext()).load(restaurant[13].getImageURL()).resize(250, 250).centerInside().into(img14);
                Picasso.with(getApplicationContext()).load(restaurant[14].getImageURL()).resize(250, 250).centerInside().into(img15);
                Picasso.with(getApplicationContext()).load(restaurant[15].getImageURL()).resize(250, 250).centerInside().into(img16);
                Picasso.with(getApplicationContext()).load(restaurant[16].getImageURL()).resize(250, 250).centerInside().into(img17);
                Picasso.with(getApplicationContext()).load(restaurant[17].getImageURL()).resize(250, 250).centerInside().into(img18);
                Picasso.with(getApplicationContext()).load(restaurant[18].getImageURL()).resize(250, 250).centerInside().into(img19);
                Picasso.with(getApplicationContext()).load(restaurant[19].getImageURL()).resize(250, 250).centerInside().into(img20);

                for (final ImageView imageView : images) {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (Integer.parseInt(imageView.getTag().toString())) {
                                case 0:
                                    index = 0;
                                    break;
                                case 1:
                                    index = 1;
                                    break;
                                case 2:
                                    index = 2;
                                    break;
                                case 3:
                                    index = 3;
                                    break;
                                case 4:
                                    index = 4;
                                    break;
                                case 5:
                                    index = 5;
                                    break;
                                case 6:
                                    index = 6;
                                    break;
                                case 7:
                                    index = 7;
                                    break;
                                case 8:
                                    index = 8;
                                    break;
                                case 9:
                                    index = 9;
                                    break;
                                case 10:
                                    index = 10;
                                    break;
                                case 11:
                                    index = 11;
                                    break;
                                case 12:
                                    index = 12;
                                    break;
                                case 13:
                                    index = 13;
                                    break;
                                case 14:
                                    index = 14;
                                    break;
                                case 15:
                                    index = 15;
                                    break;
                                case 16:
                                    index = 16;
                                    break;
                                case 17:
                                    index = 17;
                                    break;
                                case 18:
                                    index = 18;
                                    break;
                                case 19:
                                    index = 19;
                                    break;
                                case 20:
                                    index = 20;
                                    break;
                                default:
                                    break;

                            }
                            index--;
                            Intent toRestaurantDetails = new Intent(Restaurant_Results.this, RestaurantDetails.class);
                            toRestaurantDetails.putExtra("name", restaurant[index].getName());
                            toRestaurantDetails.putExtra("address", restaurant[index].getAddress());
                            toRestaurantDetails.putExtra("phoneNumber", restaurant[index].getPhoneNumber());
                            toRestaurantDetails.putExtra("imageURL", restaurant[index].getImageURL());
                            toRestaurantDetails.putExtra("city", restaurant[index].getCity());
                            toRestaurantDetails.putExtra("state", restaurant[index].getState());
                            toRestaurantDetails.putExtra("reviewSnippet", restaurant[index].getReviewSnippet());
                            toRestaurantDetails.putExtra("distance", restaurant[index].getDistance());
                            toRestaurantDetails.putExtra("ratingURL", restaurant[index].getRatingURL());
                            toRestaurantDetails.putExtra("term", term);
                            startActivity(toRestaurantDetails);
                        }

                    });
                }
            }


            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
                TextView connectionTest = (TextView) findViewById(R.id.test);
                Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lemon-Regular.ttf");
                connectionTest.setTypeface(customFont);
                con_test = "No connection. please try with a different city";
                connectionTest.setText(con_test);
            }

        };

        // make the asynchronous request
        call.enqueue(callback);
    }

}

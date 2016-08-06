/**
 * Created by Rodrigo Figueroa, David Dao,
 Diana Galvan, and Sara Lipowsky  on 7/14/16.
 */
package com.example.rui.location;

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

/*****************************************************
 * this class is responsible for showing the Restaurant
 * result search
 *************************************************** */

public class RestaurantResults extends AppCompatActivity {

    //initialize variables
    private String cityLocation;
    private double lat = 33.873825;
    private double lon = -117.924372;

    // default parameters
    private String term = ""; //used to always look for food places
    private String numberOfResults = "20"; //limit the number of results to 20 businesses
    private String category_filter = "food"; //category to look for
    private String radius_filter="40000"; //max radius filter (25 miles)
    private String sort="1"; //default sort for closest first



    Restaurant[] restaurant; // array to temporarily hold restaurants from yelp APi

    static int index = 0;

    private GoogleApiClient client;

    int search_category;

    /*************************************************************************
    * protected void onCreate
    * activity file used to create the second  screen for the application
    * and retrieve data from previous screen
    *
    * input:
    * savedInstanceState: parameter to pass data from one activity to another
    *
    *  return: none
     **************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        restaurant = new Restaurant[Integer.parseInt(numberOfResults)];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__results);

        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();
        this.term = getTerm.getString("term");
        this.cityLocation = getTerm.getString("cityLocation");


        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lemon-Regular.ttf");
        String con_test = "";
        yelp();

    }

    /*************************************************************************
     * private void getCoordinates()
     * method used to pull latitude and longitude from gps coordinates
     *
     * input: none
     *
     *  return: none
     **************************************************************************/
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

    /*************************************************************************
     * private void yelp()
     * used to conect with the Yelp API by using respective keys.
     * it creates a map with different parameters for the search and retrieves
     * information from a JSON file. It also sets the images from the Url search
     * into a ImageView
     * input: none
     *
     *  return: none
     **************************************************************************/
    private void yelp()
    {
        //keys from yelp API
        String consumerKey = "GH0hCC83JR1G-T_7T54jxw";
        String consumerSecret = "Dw-cj6EtFAIRpu9pzSRjEuHEUNs";
        String token = "VPvzcqEfLh07yrHaAzIARouynBWnDjxv";
        String tokenSecret = "QYcm0Coq4XRfjLTSfyCv4Zlb38c";
        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        YelpAPI yelpAPI = apiFactory.createAPI();
        // call request to API
        Call<SearchResponse> call;

        //map to put parameters for the search
        Map<String, String> params = new HashMap<>();

        // general params for restaurant search
        params.put("term", term);
        params.put("limit", numberOfResults);
        params.put("category_filter", category_filter);
        params.put("radius_filter", radius_filter);
        params.put("sort", sort);

        //check if user inputed a location on prevous screen
        if (cityLocation != null && !cityLocation.isEmpty()) {
            // if the user entered a city location, use that for the search
              call = yelpAPI.search(cityLocation, params);
        } else
        {
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

            String con_test = "";//conection test string
            /*************************************************************************
             * public void onResponse()
             * used to connect if conection to the server is granted, a JSON (Java) object
             * with multiple urls is returned.
             *
             * input:
             *      call
             *      response
             *
             *  return: none
             **************************************************************************/
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                //find all the Xml elements in the activity by id
                TextView connectionTest = (TextView) findViewById(R.id.test);
                Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lemon-Regular.ttf");
                connectionTest.setTypeface(customFont);
                con_test = "Results based on your input:";
                connectionTest.setText(con_test);

                restaurant = new Restaurant[Integer.parseInt(numberOfResults)];
                // array list to store and refer to Xml image views
                ArrayList<ImageView> images = new ArrayList<>();
                // add image views to array list of images
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

                //parse java object from search (call)
                SearchResponse searchResponse = response.body();
                final ArrayList<Business> businesses = searchResponse.businesses();

                // save results to Restaurants array
                for (int i = 0; i < Integer.parseInt(numberOfResults); i++) {
                    String address;
                    double dist;
                    //check for address and restaurant location for a restaurant,
                    // if it is not available set it to 'no address available' and distance =0
                    //else get the address and restaurant location
                    try {
                        address = businesses.get(i).location().address().get(0);
                        dist = businesses.get(i).distance();
                    } catch (Exception e) {
                        address = "No address available";
                        dist = 0;
                    }

                    // create restaurant object
                    restaurant[i] = new Restaurant(businesses.get(i).name(), businesses.get(i).phone(),
                            address, dist, businesses.get(i).snippetText(),
                            businesses.get(i).imageUrl(), businesses.get(i).location().city(),
                            businesses.get(i).location().stateCode(),
                            businesses.get(i).ratingImgUrlLarge(),
                            businesses.get(i).location().postalCode());
                }

                // use Picasso to convert URLs from restaurants to bit images
                Picasso.with(getApplicationContext()).load(restaurant[0].getImageURL()).resize(250, 250).centerInside().into(images.get(0));
                Picasso.with(getApplicationContext()).load(restaurant[1].getImageURL()).resize(250, 250).centerInside().into(images.get(1));
                Picasso.with(getApplicationContext()).load(restaurant[2].getImageURL()).resize(250, 250).centerInside().into(images.get(2));
                Picasso.with(getApplicationContext()).load(restaurant[3].getImageURL()).resize(250, 250).centerInside().into(images.get(3));
                Picasso.with(getApplicationContext()).load(restaurant[4].getImageURL()).resize(250, 250).centerInside().into(images.get(4));
                Picasso.with(getApplicationContext()).load(restaurant[5].getImageURL()).resize(250, 250).centerInside().into(images.get(5));
                Picasso.with(getApplicationContext()).load(restaurant[6].getImageURL()).resize(250, 250).centerInside().into(images.get(6));
                Picasso.with(getApplicationContext()).load(restaurant[7].getImageURL()).resize(250, 250).centerInside().into(images.get(7));
                Picasso.with(getApplicationContext()).load(restaurant[8].getImageURL()).resize(250, 250).centerInside().into(images.get(8));
                Picasso.with(getApplicationContext()).load(restaurant[9].getImageURL()).resize(250, 250).centerInside().into(images.get(9));
                Picasso.with(getApplicationContext()).load(restaurant[10].getImageURL()).resize(250, 250).centerInside().into(images.get(10));
                Picasso.with(getApplicationContext()).load(restaurant[11].getImageURL()).resize(250, 250).centerInside().into(images.get(11));
                Picasso.with(getApplicationContext()).load(restaurant[12].getImageURL()).resize(250, 250).centerInside().into(images.get(12));
                Picasso.with(getApplicationContext()).load(restaurant[13].getImageURL()).resize(250, 250).centerInside().into(images.get(13));
                Picasso.with(getApplicationContext()).load(restaurant[14].getImageURL()).resize(250, 250).centerInside().into(images.get(14));
                Picasso.with(getApplicationContext()).load(restaurant[15].getImageURL()).resize(250, 250).centerInside().into(images.get(15));
                Picasso.with(getApplicationContext()).load(restaurant[16].getImageURL()).resize(250, 250).centerInside().into(images.get(16));
                Picasso.with(getApplicationContext()).load(restaurant[17].getImageURL()).resize(250, 250).centerInside().into(images.get(17));
                Picasso.with(getApplicationContext()).load(restaurant[18].getImageURL()).resize(250, 250).centerInside().into(images.get(18));
                Picasso.with(getApplicationContext()).load(restaurant[19].getImageURL()).resize(250, 250).centerInside().into(images.get(19));

                //check if an image was clicked  and go to the next screen to show details
                for (final ImageView imageView : images) {
                    imageView.setOnClickListener(new View.OnClickListener() {

                        /*************************************************************************
                         * public void onClick (View view)
                         *     check if an image was clicked and forwards data to next screen
                         *
                         * input:
                         *      view, a listener for on click action
                         *
                         *  return: none
                         **************************************************************************/
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

                            //pass information to next screen
                            Intent toRestaurantDetails = new Intent(RestaurantResults.this, RestaurantDetails.class);
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


            /*************************************************************************
             *  public void onFailure(Call<SearchResponse> call, Throwable t) {
             * check connection with server, if fails print a message for user to try again
             *
             * input:
             *      call a call to the server
             *      t a Throwable exception
             *
             *  return: none
             **************************************************************************/

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

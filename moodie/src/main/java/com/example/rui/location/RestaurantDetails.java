/**
 * Created by Rodrigo Figueroa, David Dao,
 Diana Galvan, and Sara Lipowsky  on 7/14/16.
 */

package com.example.rui.location;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
/*****************************************************
 * this class is responsible for showing details of
 * Restaurants
 *************************************************** */

public class RestaurantDetails extends AppCompatActivity {
    //declaration of local variables
    private String name;
    private String address;
    private String phoneNumber;
    private double distance;
    private String city;
    private String imageURL;
    private String reviewSnippet;
    private String state;
    private String ratingURL;
    private String term;
    private String cityLocation;

    /*************************************************************************
     * protected void onCreate
     * activity file used to create the third screen for the application and
     * retrieve data from previous screen
     *
     * input:
     * savedInstanceState: parameter to pass data from one activity to another
     *
     *  return: none
     **************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();

        this.name = getTerm.getString("name");
        this.imageURL = getTerm.getString("imageURL");
        this.address = getTerm.getString("address");
        this.phoneNumber = getTerm.getString("phoneNumber");
        this.distance = getTerm.getDouble("distance");
        this.city = getTerm.getString("city");
        this.state = getTerm.getString("state");
        this.reviewSnippet = getTerm.getString("reviewSnippet");
        this.ratingURL=getTerm.getString("ratingURL");
        this.term = getTerm.getString("term");
        this.cityLocation = getTerm.getString("cityLocation");


        setUp();
    }

    /*************************************************************************
     * private void setUp()
     * method to find data from the xml file  and set data on their corresponding
     * xml fields
     *
     * input: none
     *
     *  return: none
     **************************************************************************/
    private void setUp()
    {
        //variables to be displayed
        String RestaurantPhone = "Phone Number: " + this.phoneNumber + "\n";
        String RestaurantAddress = "Address: " + this.address + "\n";
        String RestaurantReview ="Review: " + this.reviewSnippet + "\n";

        //get fields from the xml file
        TextView restName = (TextView) findViewById(R.id.restaurantName);
        ImageView img = (ImageView) findViewById(R.id.image);
        ImageView ratingURLimg = (ImageView) findViewById(R.id.ratingUrl);
        TextView restPhone = (TextView) findViewById(R.id.phoneNum);
        TextView restAddress = (TextView) findViewById(R.id.address);
        TextView restReview = (TextView) findViewById(R.id.review);



        //change font of restaurant name
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lemon-Regular.ttf");
        restName.setTypeface(customFont);
        restName.setText(name);

        //set information on fields
        restPhone.setText(RestaurantPhone);
        restAddress.setText(RestaurantAddress);
        restReview.setText(RestaurantReview);
        Picasso.with(getApplicationContext()).load(imageURL).into(img);
        Picasso.with(getApplicationContext()).load(ratingURL).into(ratingURLimg);

    }

}

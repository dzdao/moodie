package com.example.rui.location;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RestaurantDetails extends AppCompatActivity {
    String name;
    String address;
    String phoneNumber;
    double distance;
    String city;
    String imageURL;
    String reviewSnippet;
    String state;
    String ratingURL;

    String term;
    String cityLocation;

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

        String RestaurantDataName = name;
        String RestaurantData2 = "Phone Number: " +
                this.phoneNumber + "\n\n" +
                "Address: " +
                this.address + "\n\n" +
                "Review: " +
                this.reviewSnippet + "\n\n";

        ImageView img = (ImageView) findViewById(R.id.image);
        ImageView ratingURLimg = (ImageView) findViewById(R.id.ratingUrl);

        Picasso.with(getApplicationContext()).load(imageURL).into(img);
        Picasso.with(getApplicationContext()).load(ratingURL).into(ratingURLimg);


        TextView restName = (TextView) findViewById(R.id.restaurantName);
        //change font of restaurant name
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/FFF_Tusj.ttf");
        restName.setTypeface(customFont);
        restName.setText(name);

        TextView dets2 = (TextView) findViewById(R.id.details2);
        dets2.setText(RestaurantData2);

        this.term = getTerm.getString("term");
        this.cityLocation = getTerm.getString("cityLocation");

    }

}

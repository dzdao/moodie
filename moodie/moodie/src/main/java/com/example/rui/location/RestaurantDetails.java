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

public class RestaurantDetails extends AppCompatActivity {
     String name;
     String address;
    String phoneNumber;
    double distance;
     String city;
     String imageURL;
     String reviewSnippet;
     String state;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();
        this.name=getTerm.getString("name");
        this.address=getTerm.getString("address");
        this.phoneNumber=getTerm.getString("phoneNumber");
        this.distance=getTerm.getDouble("distance");
        this.state=getTerm.getString("state");
        this.city=getTerm.getString("city");
        this.reviewSnippet=getTerm.getString("reviewSnippet");

        this.imageURL=getTerm.getString("imageURL");

        String RestaurantData=name +'\n' +
                this.phoneNumber+'\n'+
                this.address +'\n'+
                this.city+", "+this.state+'\n'+
                this.reviewSnippet+'\n'+'\n'+
                this.imageURL;

        TextView dets = (TextView) findViewById(R.id.details);
        dets.setText(RestaurantData);

    }

}

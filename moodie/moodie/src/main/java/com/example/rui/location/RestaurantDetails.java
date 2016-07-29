package com.example.rui.location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

        String RestaurantData="Restaurant Name: " + name +"\n\n" +
                "Phone Number: " +
                this.phoneNumber+"\n\n"+
                "Address: " +
                this.address +'\n'+
                "                 " +
                this.city+", "+this.state+"\n\n" +
                "Review: " +
                this.reviewSnippet+'\n'+'\n'+
                this.imageURL;

        TextView dets = (TextView) findViewById(R.id.details);
        dets.setText(RestaurantData);

    }

}

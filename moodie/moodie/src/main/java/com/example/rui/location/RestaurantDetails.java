package com.example.rui.location;

import android.content.Intent;
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

    String term;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        //get data from previous activity
        Bundle getTerm = getIntent().getExtras();

        this.name=getTerm.getString("name");
        this.imageURL=getTerm.getString("imageURL");
        this.address=getTerm.getString("address");
        this.phoneNumber=getTerm.getString("phoneNumber");
        this.distance=getTerm.getDouble("distance");
        this.state=getTerm.getString("state");
        this.city=getTerm.getString("city");
        this.reviewSnippet=getTerm.getString("reviewSnippet");

        String RestaurantDataName= name;
        String RestaurantData2= "Phone Number: " +
                this.phoneNumber+"\n\n"+
                "Address: " +
                this.address +'\n'+
                "                 " +
                this.city+", "+this.state+"\n\n" +
                "Review: " +
                this.reviewSnippet+ "\n\n";

        ImageView img = (ImageView) findViewById(R.id.image);

        Picasso.with(getApplicationContext()).load(imageURL).into(img);


        TextView dets = (TextView) findViewById(R.id.details);
        dets.setText(RestaurantDataName);

        TextView dets2 = (TextView) findViewById(R.id.details2);
        dets2.setText(RestaurantData2);

        this.term=getTerm.getString("term");

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, Restaurant_Results.class);
        intent.putExtra("term",term);
        startActivity(intent);
    }

}
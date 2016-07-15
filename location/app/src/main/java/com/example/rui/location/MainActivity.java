package com.example.rui.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BoundingBoxOptions;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.security.Provider;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

 String location = "you're in: ";
    double lat;
    double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        YelpAPIFactory apiFactory = new YelpAPIFactory("GH0hCC83JR1G-T_7T54jxw", "Dw-cj6EtFAIRpu9pzSRjEuHEUNs", "LFzLyFa_z8Id4q9TvwfFgE-StpfbU4LN", "5lNteBpwcpLj8vBCJprPsnXEbL8");
        YelpAPI yelpAPI = apiFactory.createAPI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCoordinates();
        updateCoordinates(lat, lon);
    }

    private void updateCoordinates(double lat, double lon)
    {
        TextView text= (TextView) findViewById(R.id.textLocation);
        String currentLocation = location + "Latitude: "+ lat +" Longitude: " + lon;
        text.setText ( currentLocation );

    }

    private void getCoordinates()
    {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lon = loc.getLongitude();
        lat =  loc.getLatitude();
        if(lat == 0)
        {
            lat=100;
        }
        if( lon == 0)
        {
            lon=100;
        }
    }

}

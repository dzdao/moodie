package com.example.rui.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.security.Provider;

public class MainActivity extends AppCompatActivity
{
 String location = "you're in: ";
    double lat;
    double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

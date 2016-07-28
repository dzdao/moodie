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


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button mood1=(Button) findViewById(R.id.mood1);
        final Button mood2=(Button) findViewById(R.id.mood2);

        mood1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            { Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
                startActivity(toRestaurantResults);
                return false;
            }
        });

    }


}

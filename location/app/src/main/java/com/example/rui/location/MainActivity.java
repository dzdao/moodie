package com.example.rui.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;
import com.yelp.clientlib.entities.options.BoundingBoxOptions;
import com.yelp.clientlib.entities.options.CoordinateOptions;

import java.security.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String location = "you're in: ";
    double lat;
    double lon;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCoordinates();
        updateCoordinates();
        yelp();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void updateCoordinates() {
        TextView text = (TextView) findViewById(R.id.textLocation);
        String currentLocation = location + "Latitude: " + lat + " Longitude: " + lon;
        text.setText(currentLocation);

    }

    private void getCoordinates() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lon = loc.getLongitude();
        lat = loc.getLatitude();
        if (lat == 0) {
            lat = 100;
        }
        if (lon == 0) {
            lon = 100;
        }
    }

    private void yelp()
    {
        YelpAPIFactory apiFactory = new YelpAPIFactory("GH0hCC83JR1G-T_7T54jxw", "Dw-cj6EtFAIRpu9pzSRjEuHEUNs", "LFzLyFa_z8Id4q9TvwfFgE-StpfbU4LN", "5lNteBpwcpLj8vBCJprPsnXEbL8");
        YelpAPI yelpAPI = apiFactory.createAPI();

        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", "food");
        params.put("limit", "3");

        // locale params
        params.put("lang", "fr");

        CoordinateOptions coordinate = CoordinateOptions.builder()
                .latitude(lat)
                .longitude(lon).build();
        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
        // Response<SearchResponse> response = call.execute();
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            TextView con = (TextView) findViewById(R.id.connection);
            String con_test="not goin";

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                // Update UI text with the searchResponse.
                con_test="CONNECTED!!!!!!                     "+'\n'
                        + searchResponse.toString();
                con.setText(con_test);


            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // HTTP error happened, do something to handle it.
                con_test= "fail";
                con.setText(con_test);
            }


        };

        call.enqueue(callback);

        //sample
       String[]  myStringArray={"item 1","item 2","item3" ,"item 4"};
        ArrayAdapter<String> myAdapter=new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myStringArray);
        ListView myList=(ListView)
                findViewById(R.id.listView);
       // myList.setAdapter(myAdapter);


    }

}

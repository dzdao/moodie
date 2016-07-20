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
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Spinner;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    String location = "you're @ ";
//    double lat = 0.0;
//    double lon = 0.0;
//
//    // defualt parameters
//    String term = "hot and new"; //used to always look for food places
//    String numberOfResults = "5"; //return 5 businesses
//    String category_filter = "food";
//
//
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    private GoogleApiClient client;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //getCoordinates();
//        //updateCoordinates();
//        //yelp();
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//    }
//
//    private void updateCoordinates()
//    {
//        TextView text = (TextView) findViewById(R.id.textLocation);
//        String currentLocation = location + "Latitude: " + lat + " Longitude: " + lon;
//        text.setText(currentLocation);
//
//    }
//
//
//    private void getCoordinates()
//    {
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//        if(loc==null)
//        {
//            loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if(loc==null)
//            {
//                lon=100;
//                lat=100;
//            }
//        }
//        lon = loc.getLongitude();
//        lat = loc.getLatitude();
//    }
//
//    private void yelp()
//    {
//        YelpAPIFactory apiFactory = new YelpAPIFactory("GH0hCC83JR1G-T_7T54jxw", "Dw-cj6EtFAIRpu9pzSRjEuHEUNs", "LFzLyFa_z8Id4q9TvwfFgE-StpfbU4LN", "5lNteBpwcpLj8vBCJprPsnXEbL8");
//        YelpAPI yelpAPI = apiFactory.createAPI();
//
//        Map<String, String> params = new HashMap<>();
//
//        // general params
//        params.put("term", term);
//        params.put("limit", numberOfResults);
//        params.put("category_filter",category_filter);
//
//        CoordinateOptions coordinate = CoordinateOptions.builder()
//                .latitude(lat)
//                .longitude(lon).build();
//        Call<SearchResponse> call = yelpAPI.search(coordinate, params);
//        // Response<SearchResponse> response = call.execute();
//        Callback<SearchResponse> callback = new Callback<SearchResponse>()
//        {
//            TextView con = (TextView) findViewById(R.id.connection);
//            String con_test="not goin";
//
//            @Override
//            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response)
//            {
//                SearchResponse searchResponse = response.body();
//                // Update UI text with the searchResponse.
//                con_test="CONNECTED!!!!!!                     "+'\n'
//                        + searchResponse.toString();
//                con.setText(con_test);
//
//            }
//
//            @Override
//            public void onFailure(Call<SearchResponse> call, Throwable t)
//            {
//                // HTTP error happened, do something to handle it.
//                con_test= "fail";
//                con.setText(con_test);
//            }
//
//        };
//
//        call.enqueue(callback);
//
//    }

    String location;
    int search_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input_location = (EditText)findViewById(R.id.inputLocation);
        final Spinner category_filter = (Spinner)findViewById(R.id.categories);
        Button search_Btn = (Button)findViewById(R.id.searchBtn);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        search_Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                location = input_location.getText().toString();
                search_category = category_filter.getSelectedItemPosition();

                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("key1", location);
                editor.putInt("key2", search_category);
                editor.commit();

                startActivity(new Intent(MainActivity.this, ResultsActivity.class));
            }
        });
    }

}

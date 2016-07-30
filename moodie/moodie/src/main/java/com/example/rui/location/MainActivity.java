package com.example.rui.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity
{
    String searchTermForHappyMood = "entertainment";
    String searchTermForSadMood = "dessert";
    String searchTermForadventurousMood = "food";
    String searchTermForhealthyMood = "healthy";
    String cityLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton mood1 = (ImageButton) findViewById(R.id.mood1); // button for happy mood
        final ImageButton mood2 = (ImageButton) findViewById(R.id.mood2); // button for sad mood
        final ImageButton mood3 = (ImageButton) findViewById(R.id.mood3); // button for adventurous mood
        final ImageButton mood4 = (ImageButton) findViewById(R.id.mood4); // button for healthy mood

        Mood happy = new Mood(this, this, "happy", mood1);
        happy.getGiphy();
        Mood sad = new Mood(this, this, "sad", mood2);
        sad.getGiphy();
        Mood adventurous = new Mood(this, this, "adventurous", mood3);
        adventurous.getGiphy();
        Mood healthy = new Mood(this, this, "healthy", mood4);
        healthy.getGiphy();



    }

    // on click, send request for a term to next activity (using xml to call function)
    public void buttonClicked(View v) {

        Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
        final EditText specLocation = (EditText) findViewById(R.id.inputLocation); // EditText for optional specified location
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // grab the city location from EditText if available
        cityLocation = specLocation.getText().toString();
        toRestaurantResults.putExtra("cityLocation", cityLocation);

        switch (v.getId()) {
            case (R.id.mood1):
                toRestaurantResults.putExtra("term", searchTermForHappyMood);
                break;
            case (R.id.mood2):
                toRestaurantResults.putExtra("term", searchTermForSadMood);
                break;
            case (R.id.mood3):
                toRestaurantResults.putExtra("term", searchTermForadventurousMood);
                break;
            default:
                toRestaurantResults.putExtra("term", searchTermForhealthyMood);
                break;
        }
        startActivity(toRestaurantResults);
    }
}

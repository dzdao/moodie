package com.example.rui.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    String searchTermForHappyMood = "entertainment";
    String searchTermForSadMood = "dessert";
    String searchTermForAdventurousMood = "food";
    String searchTermForHealthyMood = "healthy";
    String cityLocation;

    String moodTermOne="happy";
    String moodTermTwo="sad";
    String moodTermThree="brave";
    String moodTermFour="healthy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton mood1 = (ImageButton) findViewById(R.id.mood1); // button for happy mood
        final ImageButton mood2 = (ImageButton) findViewById(R.id.mood2); // button for sad mood
        final ImageButton mood3 = (ImageButton) findViewById(R.id.mood3); // button for adventurous mood
        final ImageButton mood4 = (ImageButton) findViewById(R.id.mood4); // button for healthy mood

        //find text views and set them according to mood search
        TextView mood1Text =(TextView)findViewById(R.id.buttonOneText);
        TextView mood2Text =(TextView)findViewById(R.id.buttonTwoText);
        TextView mood3Text =(TextView)findViewById(R.id.buttonThreeText);
        TextView mood4Text =(TextView)findViewById(R.id.buttonFourText);
        mood1Text.setText("#"+moodTermOne);
        mood2Text.setText("#"+moodTermTwo);
        mood3Text.setText("#"+moodTermThree);
        mood4Text.setText("#"+moodTermFour);


        Mood happy = new Mood(this, this, moodTermOne, mood1);
        happy.getGiphy();
        Mood sad = new Mood(this, this, moodTermTwo, mood2);
        sad.getGiphy();
        Mood adventurous = new Mood(this, this, moodTermThree, mood3);
        adventurous.getGiphy();
        Mood healthy = new Mood(this, this, moodTermFour , mood4);
        healthy.getGiphy();



    }

    // on click, send request for a term to next activity (using xml to call function)
    public void buttonClicked(View v) {
        Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);
        switch (v.getId()) {
            case (R.id.mood1):
                toRestaurantResults.putExtra("term", searchTermForHappyMood);
                Toast.makeText(MainActivity.this, "Results for "+moodTermOne.toUpperCase(), Toast.LENGTH_LONG).show();
                break;
            case (R.id.mood2):
                toRestaurantResults.putExtra("term", searchTermForSadMood);
                Toast.makeText(MainActivity.this, "Results for "+moodTermTwo.toUpperCase(), Toast.LENGTH_LONG).show();
                break;
            case (R.id.mood3):
                toRestaurantResults.putExtra("term", searchTermForAdventurousMood);
                Toast.makeText(MainActivity.this, "Results for "+moodTermThree.toUpperCase(), Toast.LENGTH_LONG).show();
                break;
            default:
                toRestaurantResults.putExtra("term", searchTermForHealthyMood);
                Toast.makeText(MainActivity.this, "Results for "+moodTermFour.toUpperCase(), Toast.LENGTH_LONG).show();
                break;
        }
        startActivity(toRestaurantResults);
    }
}
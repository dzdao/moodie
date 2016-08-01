package com.example.rui.location;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    String moodTermThree="adventurous";
    String moodTermFour="healthy";

    String messageString= "Hungry? Pick a mood:";

    boolean internetConnection;

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
        TextView message =(TextView)findViewById(R.id.message);

        //assign font
        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Lemon-Regular.ttf");
        mood1Text.setTypeface(customFont);
        mood2Text .setTypeface(customFont);
        mood3Text.setTypeface(customFont);
        mood4Text.setTypeface(customFont);
        message.setTypeface(customFont);

        mood1Text.setText("#"+moodTermOne);
        mood2Text.setText("#"+moodTermTwo);
        mood3Text.setText("#"+moodTermThree);
        mood4Text.setText("#"+moodTermFour);
        message.setText(messageString);


            //assign GIFs to image buttons
            Mood happy = new Mood(this, this, moodTermOne, mood1);
            happy.getGiphy();
            Mood sad = new Mood(this, this, moodTermTwo, mood2);
            sad.getGiphy();
            Mood adventurous = new Mood(this, this, moodTermThree, mood3);
            adventurous.getGiphy();
            Mood healthy = new Mood(this, this, moodTermFour, mood4);
            healthy.getGiphy();

        //logo details
        ImageView logoImage= (ImageView) findViewById(R.id.logo);
        Drawable myDrawable = getResources().getDrawable(R.drawable.logo);
        logoImage.setImageDrawable(myDrawable);



    }

    // on click, send request for a term to next activity (using xml to call function)
    public void buttonClicked(View v) {
        Intent toRestaurantResults = new Intent(MainActivity.this, Restaurant_Results.class);

        // pull and store the location the user typed
        EditText specificLocation = (EditText) findViewById(R.id.inputLocation);
        cityLocation = specificLocation.getText().toString();
        toRestaurantResults.putExtra("cityLocation", cityLocation);

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

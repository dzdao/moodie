package com.example.rui.location;

import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rui on 7/21/16.
 */
public class Restaurant
{


    private String name;
    private String address;
    private String phoneNumber;
    private double distance;
    private String city;
    private String imageURL;
    private String reviewSnippet;
    private String state;

    public Restaurant( ArrayList<Business> businesses, int i)
    {
        name = businesses.get(i).name();
        try {
            address = businesses.get(i).location().address().get(0);
        }
        catch (Exception e){
            address = "no address available";
        }
        phoneNumber = businesses.get(i).displayPhone();

        distance = businesses.get(i).distance();

        // convert meters to miles
        distance = distance / 1609.34;

        reviewSnippet = businesses.get(i).snippetText();

        imageURL = businesses.get(i).imageUrl();

        city = businesses.get(i).location().city();
        state = businesses.get(i).location().stateCode();


    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getDistance() {
        return distance;
    }

    public String getCity() {
        return city;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getReviewSnippet() {
        return reviewSnippet;
    }


    public String getName()
    {
        return  name;
    }
    public String restaurantImage()
    {
        return imageURL;
    }
    public String getState()
    {
        return state;
    }
    public String toString()
    {
        return this.getName() +'\n' +
                this.getPhoneNumber()+'\n'+
                this.getAddress() +'\n'+
                this.getCity()+", "+this.getState()+'\n'+
                this.getReviewSnippet();
    }
}

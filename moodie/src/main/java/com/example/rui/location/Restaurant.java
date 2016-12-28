/**
 * Created by Rodrigo Figueroa, David Dao,
 Diana Galvan, and Sara Lipowsky  on 7/27/16.
 */

package com.example.rui.location;
/**************************************************************************
 * This class is responsable for holding information about restaurant
 ***************************************************************************/
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
    private String ratingURL;
    private String zipCode;

    /*************************************************************************
     *  constructor
     *      Restaurant
     * input:
     * name a string type for the name of the restaurant
     * phoneNumber a string type for the phone of the restaurant
     * address a string type for the address of the restaurant
     * distance a double containg the distance from the user input to the resstaurant
     * reviewSnippet a string type with a review for restaurant
     * imageURL a string type with a URL for an image of the restaurant
     * city a string type for the name of the city for a restaurant
     * state a string type for the name of the state for a restaurant
     * ratingURL a string type with a URL for an image rating of the restaurant
     * zipCode type for the zipCode of the state for a restaurant
     *
     *  return: none
     **************************************************************************/
    public Restaurant( String name , String phoneNumber,String address,
                       double distance, String reviewSnippet,String imageURL, String city, String state, String ratingURL, String zipCode)
    {
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.phoneNumber = phoneNumber;
        this.distance = distance;
        this.reviewSnippet =reviewSnippet;
        this.imageURL = imageURL;
        this.city =city;
        this.state = state;
        this.ratingURL=ratingURL;
        this.zipCode = zipCode;
    }

    /*************************************************************************
 *  toString()
 *  returns a string containing Phone number,  Address , City,  State, Review Snippet
 *
 * input: none
 *
 *  return: a string containing Phone number,  Address , City,  State, Review Snippet
 **************************************************************************/
    public String toString()
    {
        return
                this.getPhoneNumber()+'\n'+
                        this.getAddress() +'\n'+
                        this.getCity()+", "+this.getState()+'\n'+
                        this.getReviewSnippet();
    }

    /************************************************************************
     * Setters and getters for the class
     **************************************************************************/
    public String getAddress() {
        return address+ '\n' + city + ", " + state + " " + zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getDistance() {
        // convert meters to miles
        distance = distance / 1609.34;
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
    public String getRatingURL() {
        return ratingURL;
    }


    public String getName()
    {
        return  name;
    }
    public String getState()
    {
        return state;
    }

}

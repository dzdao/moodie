/**
 * Created by Rodrigo Figueroa, David Dao,
 Diana Galvan, and Sara Lipowsky  on 7/28/16.
 */

package com.example.rui.location;

/*****************************************************
 * Instances of these objects contain URL data retrieved
 * from the Giphy API
 *************************************************** */

public class GiphyData {

    private String url;

    /*************************************************************************
     *   public String getUrl()
     *
     * input: none
     *
     *  return: none
     **************************************************************************/

    public String getUrl() {
        return url;
    }
    /*************************************************************************
     * setUrl(String url)
     * accepts a string parameter and sets a private string attribute which
     * represents a url to an animated gif from the Giphy database
     * input:
     *      url a string with a url of a gif
     *  return: none
     **************************************************************************/

    public void setUrl(String url) {
        this.url = url;
    }
}
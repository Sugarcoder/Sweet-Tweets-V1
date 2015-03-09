package com.codepath.apps.sweettweets.models;


import org.json.JSONException;
import org.json.JSONObject;

public class User {
    // List the attributes

    private String name;
    private Long uid;
    private String screenName;
    private String profileImageUrl;


    // Implementing some Getters

    public String getName() {
        return name;
    }public Long getUid() {
        return uid;
    }public String getScreenName() {
        return screenName;
    }public String getProfileImageUrl() {
        return profileImageUrl;
    }


    // Deserialize the User Json ==> User object
    public static User fromJSON(JSONObject json) {
        User u = new User();


        // Extract and fill the values from the Json
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Return a user
        return u;
    }

}

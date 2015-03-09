package com.codepath.apps.sweettweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


// #1. Parse the JSon + Store the data
// #2. Encapsulate the state logic or display logic

public class Tweet {
    // Stores all of the attributes of our data

    // List all of the attributes

    private String body;
    private Long uid;          // This is the unique ID for the tweet
    private User user;         // Store an embedded user object
    private String createdAt;


    // Implementing some Getters

    public User getUser() {
        return user;
    }


    public String getCreatedAt() {
        return createdAt;
    }


    public String getBody() {
        return body;
    }


    public Long getUid() {
        return uid;
    }



    // Deserialize the JSON and build tweet objects (turn it into an object)

    // Tweet.fromJSON("{ ... }") ==> <Tweet>


    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        // Extract the values from the Json, store them
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Return the stored object that we created
        return tweet;
    }



    // Tweet.fromJSONArray([{... }, { ... }]) ==> List<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        // Iterate the Json array and create tweets
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if (tweet != null) {
                    tweets.add(tweet);
                }


            } catch (JSONException e) {
                e.printStackTrace();
                continue;           // Even if one tweet doesn't work, it will process the other tweets
            }
        }


        // Return the finished list
        return tweets;
    }

}

package com.codepath.apps.sweettweets;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.sweettweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {
    // We need access to the Twitter Client
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets; // Instantiating the objects
    private ListView lvTweets;
    private long max_id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#55ACEE")));
        actionBar.setIcon(R.drawable.ic_iconpeople);

        // Find the ListView
        lvTweets = (ListView) findViewById(R.id.lvTweets);

        // Create the array list (which is the data source)
        tweets = new ArrayList<>();

        // Construct the adapter from the data source
        aTweets = new TweetsArrayAdapter(this, tweets);

        // Connect the adapter to the listview
        lvTweets.setAdapter(aTweets);

        // Get the client
        client = TwitterApplication.getRestClient();       // This is important because we can get the singleton client (access the same data across all activities)

        // Popular the timeline
        populateTimeline();


        // Attach the listener to the AdapterView onCreate
        lvTweets.setOnScrollListener(new TweetEndlessScrollListener() {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                populateTimeline();
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });

    }


    // Append more data into the adapter


    // Send an API request to get the Timeline Json
    // Fill the listview by creating the tweet objects from the Json

    private void populateTimeline() {
        client.getHomeTimeline(max_id, new JsonHttpResponseHandler() {
            // Success

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("Debug", json.toString());


                // Deserialize JSON

                // Create Models and add them to the adapter

                // Load the Models into the ListView
                aTweets.addAll(Tweet.fromJSONArray(json));
                max_id = aTweets.getItem(aTweets.getCount() - 1).getUid();
            }


            // Failure
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Debug", errorResponse.toString());
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
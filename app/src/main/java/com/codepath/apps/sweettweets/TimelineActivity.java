package com.codepath.apps.sweettweets;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
    private TweetsArrayAdapter aTweets;            // Instantiating the objects
    private ListView lvTweets;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        // Find the ListView
        lvTweets = (ListView) findViewById(R.id.lvTweets);

        // Create the array list (which is the data source)
        tweets = new ArrayList<>();

        // Construct the adapter from the data source
        aTweets = new TweetsArrayAdapter(this, tweets);

        // Connect the adapter to the listview
        lvTweets.setAdapter(aTweets);

        // Get the client
        client = TwitterApplication.getRestClient();      // This is important because we can get the singleton client (access the same data across all activities)

        // Popular the timeline
        populateTimeline();
    }



    // Send an API request to get the Timeline Json
    // Fill the listview by creating the tweet objects from the Json
    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {

            // Success
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("Debug", json.toString());

                // Deserialize JSON

                // Create Models and add them to the adapter

                // Load the Models into the ListView
                aTweets.addAll(Tweet.fromJSONArray(json));
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

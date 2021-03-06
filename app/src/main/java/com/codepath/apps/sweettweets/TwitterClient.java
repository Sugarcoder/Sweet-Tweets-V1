package com.codepath.apps.sweettweets;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 */

public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "04LVhEnyDWQfovBeCwCHPshDi";       // Change this
	public static final String REST_CONSUMER_SECRET = "aSlN7OgYM4MGSNESSMPrRSOfKqLVWob1gCQNBwQPY7O8EULqtN"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsweettweets"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}


    // Method == Endpoint

    // HomeTimeline = Get the Home Timeline

    // GET https://api.twitter.com/1.1/statuses/home_timeline.json
    //   Parameters:
    //     count=25
    //     since_id=1 (returning 1 will return all tweets)


    // First step to building out any Endpoint is to create a method.

    public void getHomeTimeline(long max_id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        // Specify the params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if (max_id != 0) {
            params.put("max_id", max_id);
        }
        params.put("since_id", 1);
        // Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Need a new method for each Param


    // Composing a Tweet




	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}
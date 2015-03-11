package com.codepath.apps.sweettweets;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.sweettweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;


// Taking the Tweet objects and turning them into views, that will be displayed in the list

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }



    // Override and setup custom template  (This is the step-by-step in building an arbitrary adapter.

    // NOTE: Review the "ViewHolder" pattern (I should apply the ViewHolder pattern to every array adapter that I build)
            // ViewHolder pattern optimizes the "GetView" and improves the performance.
            // CodePath guide: AdapterViews > Using an ArrayAdapter with ListView > Improving the performance with the ViewHolder pattern



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Get the Tweets
        Tweet tweet = getItem(position);


        // 2. Find or inflate the template
        if (convertView == null) {                       // If not null, the template is not being recycled
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);        // 'false' means we are not going to insert it into the parent yet.
        }


        // 3. Find the subviews to fill the data in the template
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvTweetsBody = (TextView) convertView.findViewById(R.id.tvTweetsBody);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);


        // 4. Populate data into the subviews
        tvUserName.setText(tweet.getUser().getScreenName());
        tvTweetsBody.setText(tweet.getBody());
        tvTime.setText(tweet.getCreatedAt());
        ivProfileImage.setImageResource(android.R.color.transparent);      // Clear out the old image for a recycled view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);       // Picasso is used to load the image url, retrieve the data, and insert it into the ImageView

        // 5. Return the view to be inserted into the list
        return convertView;

    }
}

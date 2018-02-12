package com.mbds.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbds.flickster.R;
import com.mbds.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by Zacharie-EDH on 2/11/2018.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public static class ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {

        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        Movie movie = getItem(position);
        ViewHolder viewHolder;

        // Check if existing view is being reused
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            // ivImage.setImageResource(0);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        }
        else {

            viewHolder=(ViewHolder) convertView.getTag();
        }
        // Populate text
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        // Load movie images based on orientation
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(8, 8)).into(viewHolder.ivImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            Picasso.with(getContext()).load(movie.getBackdropPath()).transform(new RoundedCornersTransformation(8, 8)).into(viewHolder.ivImage);

        return convertView;
    }
}

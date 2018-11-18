package com.nano.degree.bishram.news24x7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GuardianNewsAdapter extends ArrayAdapter<GuardianNews> {


    /**
     * Constructs a new {@link GuardianNewsAdapter}.
     *
     * @param context of the app
     * @param guardianNews is the list of guardianNews, which is the data source of the adapter
     */
    GuardianNewsAdapter(Context context, List<GuardianNews> guardianNews) {
        super(context, 0, guardianNews);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        // Find the guardian news at the given position in the list of guardian newses.
        GuardianNews currentGuardianNews = getItem(position);

        // Find the TextView with view ID news_heading in the layout.
        TextView textViewHeadline = convertView.findViewById(R.id.news_heading);

        // Find the TextView with view ID news_published_date.
        TextView textViewTimestamp = convertView.findViewById(R.id.news_published_date);

        // Find the TextView with view ID news_type.
        TextView textViewSectionName = convertView.findViewById(R.id.news_type);

        assert currentGuardianNews != null;
        // Display the Headline of the current guardian news in that TextView
        textViewHeadline.setText(currentGuardianNews.getHeadline());

        String stringDate = currentGuardianNews.getTimeStamp();

        String date = stringDate.substring(0, 10);

        // Display the Time stamp of the current guardian news in that TextView
        textViewTimestamp.setText(date);

        // Display the section name of the current guardian news in that TextView
        textViewSectionName.setText(currentGuardianNews.getSectionName());

        // Return the list item view that is now showing the appropriate data
        return convertView;
    }
}
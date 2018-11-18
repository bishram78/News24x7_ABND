package com.nano.degree.bishram.news24x7;

import android.content.Context;

import java.util.List;

/**
 * Loads a list of Guardian News by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class NewsLoader extends android.content.AsyncTaskLoader<List<GuardianNews>> {

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<GuardianNews> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        return QueryUtility.fetchGuardianNews(mUrl);
    }
}

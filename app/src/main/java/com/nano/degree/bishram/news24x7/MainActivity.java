package com.nano.degree.bishram.news24x7;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<GuardianNews>> {

    /** URL for guardian news data from the Guardian data_set */
    private static final String GUARDIAN_NEWS_REQUEST_URL =
            "http://content.guardianapis.com/search?show-tags=contributor&api-key=test";

    /**
     * Constant value for the guardian loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int GUARDIAN_NEWS_LOADER_ID = 1;

    /** Adapter for the list of Guardian News */
    private GuardianNewsAdapter guardianNewsAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView guardianNewsListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        guardianNewsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of guardian news as input
        guardianNewsAdapter = new GuardianNewsAdapter(this, new ArrayList<GuardianNews>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        guardianNewsListView.setAdapter(guardianNewsAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected guardian news.
        guardianNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current guardian news that was clicked on
                GuardianNews currentGuardianNews = guardianNewsAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                assert currentGuardianNews != null;
                Uri guardianNewsUri = Uri.parse(currentGuardianNews.getNewsUrl());

                // Create a new intent to view the guardian news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, guardianNewsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(GUARDIAN_NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<GuardianNews>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, GUARDIAN_NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<GuardianNews>> loader, List<GuardianNews> guardianNews) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No news available."
        mEmptyStateTextView.setText(R.string.no_guardian_news);

        // Clear the adapter of previous guardian news data
        //guardianNewsAdapter.clear();

        // If there is a valid list of {@link GuardianNews}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (guardianNews != null && !guardianNews.isEmpty()) {
            guardianNewsAdapter.addAll(guardianNews);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<GuardianNews>> loader) {
        // Loader reset, so we can clear out our existing data.
        guardianNewsAdapter.clear();
    }
}

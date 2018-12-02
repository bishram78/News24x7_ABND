package com.nano.degree.bishram.news24x7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class NewsPreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.main_pref_settings);

            Preference searchTermPref = findPreference(getString(R.string.settings_search_term_key));
            bindPreferenceSummaryToValue(searchTermPref);

            Preference orderByPref = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderByPref);
        }

        public void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                    preference.getContext());
            onPreferenceChange(preference, sharedPreferences.getString(preference.getKey(), ""));
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();

            preference.setSummary(stringValue);

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int preIndex = listPreference.findIndexOfValue(stringValue);

                if (preIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[preIndex]);
                }
            } else {
                preference.setSummary(newValue.toString());
            }
            return true;
        }
    }
}

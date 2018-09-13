package com.irmansyah.kamusku.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.irmansyah.kamusku.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by irmansyah on 07/03/18.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String FIRST_RUN = "FIRST_RUN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setFirstRun(boolean firstRun) {
        mPrefs.edit().putBoolean(FIRST_RUN, firstRun).apply();
    }

    @Override
    public Boolean getFirstRun() {
        return mPrefs.getBoolean(FIRST_RUN, true);
    }
}

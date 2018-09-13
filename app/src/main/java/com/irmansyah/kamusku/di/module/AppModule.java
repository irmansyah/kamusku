package com.irmansyah.kamusku.di.module;

import android.app.Application;
import android.content.Context;

import com.irmansyah.kamusku.R;
import com.irmansyah.kamusku.data.AppDataManager;
import com.irmansyah.kamusku.data.DataManager;
import com.irmansyah.kamusku.data.local.db.AppKamusEngIndHelper;
import com.irmansyah.kamusku.data.local.db.AppKamusIndEngHelper;
import com.irmansyah.kamusku.data.local.db.DatabaseHelper;
import com.irmansyah.kamusku.data.local.db.KamusEngIndHelper;
import com.irmansyah.kamusku.data.local.db.KamusIndEngHelper;
import com.irmansyah.kamusku.data.local.prefs.AppPreferencesHelper;
import com.irmansyah.kamusku.data.local.prefs.PreferencesHelper;
import com.irmansyah.kamusku.di.DatabaseInfo;
import com.irmansyah.kamusku.di.PreferenceInfo;
import com.irmansyah.kamusku.utils.AppConstants;
import com.irmansyah.kamusku.utils.rx.AppSchedulerProvider;
import com.irmansyah.kamusku.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by irmansyah on 06/03/18.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Singleton
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    KamusEngIndHelper provideKamusHelper(AppKamusEngIndHelper appKamusHelper) {
        return appKamusHelper;
    }

    @Provides
    @Singleton
    KamusIndEngHelper provideKamusIndEngHelper(AppKamusIndEngHelper appKamusHelper) {
        return appKamusHelper;
    }

    @Provides
    @Singleton
    DatabaseHelper privdeDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}

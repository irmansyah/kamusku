package com.irmansyah.kamusku;

import android.app.Activity;
import android.app.Application;

import com.irmansyah.kamusku.di.component.DaggerAppComponent;
import com.irmansyah.kamusku.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by irmansyah on 06/03/18.
 */

public class KamusKuApp extends Application implements HasActivityInjector {

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

}

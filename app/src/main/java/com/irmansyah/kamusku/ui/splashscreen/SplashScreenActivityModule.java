package com.irmansyah.kamusku.ui.splashscreen;

import com.irmansyah.kamusku.data.DataManager;
import com.irmansyah.kamusku.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by irmansyah on 09/03/18.
 */
@Module
public class SplashScreenActivityModule {

    @Provides
    SplachScreenViewModel provideSplachScreenViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider) {
        return new SplachScreenViewModel(dataManager, schedulerProvider);
    }
}

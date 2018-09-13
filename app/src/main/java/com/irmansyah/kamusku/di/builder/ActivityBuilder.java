package com.irmansyah.kamusku.di.builder;

/**
 * Created by irmansyah on 06/03/18.
 */

import com.irmansyah.kamusku.ui.splashscreen.SplashScreenActivity;
import com.irmansyah.kamusku.ui.splashscreen.SplashScreenActivityModule;
import com.irmansyah.kamusku.ui.translete.TransleteActivity;
import com.irmansyah.kamusku.ui.translete.TransleteActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {SplashScreenActivityModule.class})
    abstract SplashScreenActivity bindSplashScreenActivity();

    @ContributesAndroidInjector(modules = {TransleteActivityModule.class})
    abstract TransleteActivity bindTransleteActivity();
}

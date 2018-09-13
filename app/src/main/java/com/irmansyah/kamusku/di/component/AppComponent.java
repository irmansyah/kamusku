package com.irmansyah.kamusku.di.component;

import android.app.Application;

import com.irmansyah.kamusku.KamusKuApp;
import com.irmansyah.kamusku.di.builder.ActivityBuilder;
import com.irmansyah.kamusku.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by irmansyah on 06/03/18.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(KamusKuApp app);

}

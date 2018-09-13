package com.irmansyah.kamusku.ui.translete;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.irmansyah.kamusku.ViewModelProviderFactory;
import com.irmansyah.kamusku.data.DataManager;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;
import com.irmansyah.kamusku.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by irmansyah on 08/03/18.
 */
@Module
public class TransleteActivityModule {

    @Provides
    TransleteViewModel provideMainViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider) {
        return new TransleteViewModel(dataManager, schedulerProvider);
    }

    @Provides
    EngIndAdapter provideEngIndAdapter() {
        return new EngIndAdapter(new ArrayList<EnglishIndonesia>());
    }

    @Provides
    IndEngAdapter provideIndEngAdapter() {
        return new IndEngAdapter(new ArrayList<IndonesiaEnglish>());
    }

    @Provides
    LinearLayoutManager provideSearchLinearLayoutManager(TransleteActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    ViewModelProvider.Factory provideSearchViewModelProviderFactory(TransleteViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }
}

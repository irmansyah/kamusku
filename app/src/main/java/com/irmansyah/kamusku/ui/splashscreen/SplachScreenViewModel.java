package com.irmansyah.kamusku.ui.splashscreen;

import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.irmansyah.kamusku.data.DataManager;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.ui.base.BaseViewModel;
import com.irmansyah.kamusku.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by irmansyah on 09/03/18.
 */

public class SplachScreenViewModel extends BaseViewModel<SplashScreenNavigator> {

    private static final String TAG = "SplachScreenViewModel";

    public ObservableInt progressObs = new ObservableInt(0);
    public ObservableInt showProgress;

    public SplachScreenViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        setup();
    }

    private void setup() {
        showProgress = new ObservableInt();
        showProgress.set(View.INVISIBLE);

        doProgressDb();
    }

    public void doProgressDb() {
        Boolean firstRun = getDataManager().getFirstRun();
        if (firstRun) {
            showProgress.set(View.VISIBLE);
            getDataManager().fetchDatabaseEngInd()
                    .flatMap(englishIndonesiaList -> {
                        for (int i = 0; i < englishIndonesiaList.size(); i++) {
                            if (i % 100 == 0 || i == englishIndonesiaList.size()) {
                                progressObs.set(i);
                                Log.i(TAG, "doProgressDb: " + i);
                            }
                        }
                        return Observable.fromIterable(englishIndonesiaList);
                    })
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Observer<EnglishIndonesia>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(EnglishIndonesia englishIndonesia) {
                            Log.i(TAG, "onNext: " + englishIndonesia.getEngSearch());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "finish: ");
                            showProgress.set(View.INVISIBLE);
                            getNavigator().gotoTransleteActivity();
                        }
                    });
        }
    }
}

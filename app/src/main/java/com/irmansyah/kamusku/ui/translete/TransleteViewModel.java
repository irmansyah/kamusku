package com.irmansyah.kamusku.ui.translete;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.irmansyah.kamusku.data.DataManager;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;
import com.irmansyah.kamusku.ui.base.BaseViewModel;
import com.irmansyah.kamusku.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by irmansyah on 08/03/18.
 */

public class TransleteViewModel extends BaseViewModel<TransleteNavigator> {

    private static final String TAG = "TransleteViewModel";

    private final ObservableArrayList<EnglishIndonesia> englishIndonesiaObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<EnglishIndonesia>> englishIndonesiaListLiveData;

    private final ObservableArrayList<IndonesiaEnglish> indonesiaEnglishObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<IndonesiaEnglish>> indonesiaEnglishListLiveData;

    public ObservableField<String> resultText = new ObservableField<>("");
    public ObservableField<String> searchObs = new ObservableField<>("ENG");
    public ObservableField<String> resultObs = new ObservableField<>("IND");
    public ObservableInt showEngInd;
    public ObservableInt showIndEng;

    private Boolean typeWord;

    public TransleteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        englishIndonesiaListLiveData = new MutableLiveData<>();
        indonesiaEnglishListLiveData = new MutableLiveData<>();

        showEngInd = new ObservableInt();
        showIndEng = new ObservableInt();

        showEngInd.set(View.VISIBLE);
        showIndEng.set(View.INVISIBLE);

        typeWord = false;
    }

    public void switchLanguage() {
        getNavigator().wipeText();
        typeWord =! typeWord;
        if (typeWord == true) {
            searchObs.set("IND");
            resultObs.set("ENG");

            Log.i(TAG, "switchLanguage: ENG");

        } else if (typeWord == false) {
            searchObs.set("ENG");
            resultObs.set("IND");

            Log.i(TAG, "switchLanguage: IND");
        }
    }

    public void setTypeWord(String word) {
        if (searchObs.get().equals("ENG")) {

            showEngInd.set(View.VISIBLE);
            showIndEng.set(View.INVISIBLE);

            setSearchEngInd(word);

        } else if (searchObs.get().equals("IND")) {

            showEngInd.set(View.INVISIBLE);
            showIndEng.set(View.VISIBLE);

            setSearchIndEng(word);
        }
    }

    public void onSearchClicked() {
        getNavigator().onSearchClicked();
    }

    public void setSingleSearch(String word) {
        if (searchObs.get().equals("ENG")) {
            setSingleSearchEngInd(word);

        } else if (searchObs.get().equals("IND")) {
            setSingleSearchIndEng(word);
        }
    }

    public void setSingleSearchEngInd(String word) {
        getDataManager().openEngInd();
        getCompositeDisposable().add(getDataManager().getDataBySearchWordEngInd(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(englishIndonesias -> {

                    String search = englishIndonesias.get(0).getEngSearch();
                    String result = englishIndonesias.get(0).getIndResult();

                    resultText.set(search + " : \n" + result);

                    Log.i(TAG, "setSingleSearchEngInd: " + result);

                }, throwable -> {

                }));
        getDataManager().closeEngInd();
    }

    public void setSingleSearchIndEng(String word) {
        getDataManager().openIndEng();
        getCompositeDisposable().add(getDataManager().getDataBySearchWordIndEng(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(indonesiaEnglishes -> {

                    String search = indonesiaEnglishes.get(0).getIndSearch();
                    String result = indonesiaEnglishes.get(0).getEngResult();

                    resultText.set(search + " : \n" + result);

                    Log.i(TAG, "setSingleSearchIndEng: " + result);

                }, throwable -> {

                }));
        getDataManager().closeIndEng();
    }

    private void setSearchEngInd(String word) {
        getDataManager().openEngInd();
        getCompositeDisposable().add(getDataManager().getSearchWordEngInd(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(englishIndonesias -> {

                    englishIndonesiaListLiveData.setValue(englishIndonesias);

                }, throwable -> {

                    Log.e(TAG, "setSearchEngInd: ", throwable);

                }));
        getDataManager().closeEngInd();
    }

    private void setSearchIndEng(String word) {
        getDataManager().openIndEng();
        getCompositeDisposable().add(getDataManager().getSearchWordIndEng(word)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(indonesiaEnglishes -> {

                    indonesiaEnglishListLiveData.setValue(indonesiaEnglishes);

                }, throwable -> {

                    Log.e(TAG, "setSearchEngInd: ", throwable);

                }));
        getDataManager().closeIndEng();
    }

    public void setResultText(String text) {
        this.resultText.set(text);
    }


    public MutableLiveData<List<EnglishIndonesia>> getEnglishIndonesiaListLiveData() {
        return englishIndonesiaListLiveData;
    }

    public ObservableArrayList<EnglishIndonesia> getEnglishIndonesiaObservableArrayList() {
        return englishIndonesiaObservableArrayList;
    }

    public void addEnglishIndonesialistItemsToList(List<EnglishIndonesia> englishIndonesias) {
        englishIndonesiaObservableArrayList.clear();
        englishIndonesiaObservableArrayList.addAll(englishIndonesias);
    }

    public MutableLiveData<List<IndonesiaEnglish>> getIndonesiaEnglishListLiveData() {
        return indonesiaEnglishListLiveData;
    }

    public ObservableArrayList<IndonesiaEnglish> getIndonesiaEnglishObservableArrayList() {
        return indonesiaEnglishObservableArrayList;
    }

    public void addIndonesiaEnglishlistItemsToList(List<IndonesiaEnglish> indonesiaEnglish) {
        indonesiaEnglishObservableArrayList.clear();
        indonesiaEnglishObservableArrayList.addAll(indonesiaEnglish);
    }
}

package com.irmansyah.kamusku.data;

import android.content.Context;
import android.database.SQLException;
import android.databinding.ObservableInt;

import com.irmansyah.kamusku.data.local.db.KamusEngIndHelper;
import com.irmansyah.kamusku.data.local.db.KamusIndEngHelper;
import com.irmansyah.kamusku.data.local.prefs.PreferencesHelper;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by irmansyah on 06/03/18.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final KamusEngIndHelper mKamusEngIndHelper;
    private final KamusIndEngHelper mKamusIndEngHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context,
                          KamusEngIndHelper kamusEngIndHelper,
                          KamusIndEngHelper kamusIndEngHelper,
                          PreferencesHelper preferencesHelper) {
        this.mContext = context;
        this.mKamusEngIndHelper = kamusEngIndHelper;
        this.mKamusIndEngHelper = kamusIndEngHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public List<EnglishIndonesia> preLoadRawEngInd() {
        return mKamusEngIndHelper.preLoadRawEngInd();
    }

    @Override
    public KamusEngIndHelper openEngInd() throws SQLException {
        return mKamusEngIndHelper.openEngInd();
    }

    @Override
    public void closeEngInd() {
        mKamusEngIndHelper.closeEngInd();
    }

    @Override
    public Observable<List<EnglishIndonesia>> getDataBySearchWordEngInd(String word) {
        return mKamusEngIndHelper.getDataBySearchWordEngInd(word);
    }

    @Override
    public Observable<List<EnglishIndonesia>> getSearchWordEngInd(String word) {
        return mKamusEngIndHelper.getSearchWordEngInd(word);
    }

    @Override
    public Observable<List<EnglishIndonesia>> getAllDataEngInd() {
        return mKamusEngIndHelper.getAllDataEngInd();
    }

    @Override
    public long insertEngInd(EnglishIndonesia englishIndonesia) {
        return mKamusEngIndHelper.insertEngInd(englishIndonesia);
    }

    @Override
    public void beginTransactionEngInd() {
        mKamusEngIndHelper.beginTransactionEngInd();
    }

    @Override
    public void setTransactionSuccessEngInd() {
        mKamusEngIndHelper.setTransactionSuccessEngInd();
    }

    @Override
    public void endTransactionEngInd() {
        mKamusEngIndHelper.endTransactionEngInd();
    }

    @Override
    public void insertTransactionEngInd(EnglishIndonesia englishIndonesia) {
        mKamusEngIndHelper.insertTransactionEngInd(englishIndonesia);
    }

    @Override
    public int updateEngInd(EnglishIndonesia englishIndonesia) {
        return mKamusEngIndHelper.updateEngInd(englishIndonesia);
    }

    @Override
    public int deleteEngInd(int id) {
        return mKamusEngIndHelper.deleteEngInd(id);
    }

    @Override
    public Observable<List<EnglishIndonesia>> fetchDatabaseEngInd() {
        return mKamusEngIndHelper.fetchDatabaseEngInd();
    }

    @Override
    public void setFirstRun(boolean firstRun) {
        mPreferencesHelper.setFirstRun(firstRun);
    }

    @Override
    public Boolean getFirstRun() {
        return mPreferencesHelper.getFirstRun();
    }

    @Override
    public List<IndonesiaEnglish> preLoadRawIndEng() {
        return mKamusIndEngHelper.preLoadRawIndEng();
    }

    @Override
    public KamusIndEngHelper openIndEng() throws SQLException {
        return mKamusIndEngHelper.openIndEng();
    }

    @Override
    public void closeIndEng() {
        mKamusIndEngHelper.closeIndEng();
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getDataBySearchWordIndEng(String word) {
        return mKamusIndEngHelper.getDataBySearchWordIndEng(word);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getSearchWordIndEng(String word) {
        return mKamusIndEngHelper.getSearchWordIndEng(word);
    }

    @Override
    public Observable<List<IndonesiaEnglish>> getAllDataIndEng() {
        return mKamusIndEngHelper.getAllDataIndEng();
    }

    @Override
    public long insertIndEng(IndonesiaEnglish indonesiaEnglish) {
        return mKamusIndEngHelper.insertIndEng(indonesiaEnglish);
    }

    @Override
    public void beginTransactionIndEng() {
        mKamusIndEngHelper.beginTransactionIndEng();
    }

    @Override
    public void setTransactionSuccessIndEng() {
        mKamusIndEngHelper.setTransactionSuccessIndEng();
    }

    @Override
    public void endTransactionIndEng() {
        mKamusIndEngHelper.endTransactionIndEng();
    }

    @Override
    public void insertTransactionIndEng(IndonesiaEnglish indonesiaEnglish) {
        mKamusIndEngHelper.insertTransactionIndEng(indonesiaEnglish);
    }

    @Override
    public int updateIndEng(IndonesiaEnglish englishIndonesia) {
        return mKamusIndEngHelper.updateIndEng(englishIndonesia);
    }

    @Override
    public int deleteIndEng(int id) {
        return mKamusIndEngHelper.deleteIndEng(id);
    }

    @Override
    public Observable<Integer> fetchDatabaseIndEng(List<IndonesiaEnglish> indonesiaEnglish, ObservableInt progress) {
        return mKamusIndEngHelper.fetchDatabaseIndEng(indonesiaEnglish, progress);
    }
}

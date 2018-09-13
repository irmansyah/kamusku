package com.irmansyah.kamusku.data.local.db;

import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;

import android.database.SQLException;
import android.databinding.ObservableInt;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by irmansyah on 06/03/18.
 */

public interface KamusEngIndHelper {

    List<EnglishIndonesia> preLoadRawEngInd();

    KamusEngIndHelper openEngInd() throws SQLException;

    void closeEngInd();

    Observable<List<EnglishIndonesia>> getDataBySearchWordEngInd(String word);

    Observable<List<EnglishIndonesia>> getSearchWordEngInd(String word);

    Observable<List<EnglishIndonesia>> getAllDataEngInd();

    long insertEngInd(EnglishIndonesia englishIndonesia);

    void beginTransactionEngInd();

    void setTransactionSuccessEngInd();

    void endTransactionEngInd();

    void insertTransactionEngInd(EnglishIndonesia englishIndonesia);

    int updateEngInd(EnglishIndonesia englishIndonesia);

    int deleteEngInd(int id);

    Observable<List<EnglishIndonesia>> fetchDatabaseEngInd();

//    Observable<EnglishIndonesia> fetchDatabaseEngInd();
}

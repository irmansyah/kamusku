package com.irmansyah.kamusku.data.local.db;

import android.database.SQLException;
import android.databinding.ObservableInt;

import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by irmansyah on 08/03/18.
 */

public interface KamusIndEngHelper {

    List<IndonesiaEnglish> preLoadRawIndEng();

    KamusIndEngHelper openIndEng() throws SQLException;

    void closeIndEng();

    Observable<List<IndonesiaEnglish>> getDataBySearchWordIndEng(String word);

    Observable<List<IndonesiaEnglish>> getSearchWordIndEng(String word);

    Observable<List<IndonesiaEnglish>> getAllDataIndEng();

    long insertIndEng(IndonesiaEnglish indonesiaEnglish);

    void beginTransactionIndEng();

    void setTransactionSuccessIndEng();

    void endTransactionIndEng();

    void insertTransactionIndEng(IndonesiaEnglish indonesiaEnglish);

    int updateIndEng(IndonesiaEnglish englishIndonesia);

    int deleteIndEng(int id);

    Observable<Integer> fetchDatabaseIndEng(List<IndonesiaEnglish> indonesiaEnglish, ObservableInt progress);
}

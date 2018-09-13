package com.irmansyah.kamusku.data.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.irmansyah.kamusku.R;
import com.irmansyah.kamusku.data.local.prefs.PreferencesHelper;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.database.SQLException;
import android.databinding.ObservableInt;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.irmansyah.kamusku.data.local.db.DatabaseContract.KamusColumnsEngInd.*;
import static com.irmansyah.kamusku.data.local.db.DatabaseContract.TABLE_NAME_ENG_IND;

/**
 * Created by irmansyah on 06/03/18.
 */

public class AppKamusEngIndHelper implements KamusEngIndHelper {

    private static final String TAG = "AppKamusEngIndHelper";

    private final Context mContext;
    private final DatabaseHelper mDataBaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    private SQLiteDatabase mSQLiteDatabase;

    @Inject
    public AppKamusEngIndHelper(Context context, DatabaseHelper dataBaseHelper, PreferencesHelper preferencesHelper) {
        this.mContext = context;
        this.mDataBaseHelper = dataBaseHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public List<EnglishIndonesia> preLoadRawEngInd() {
        final ArrayList<EnglishIndonesia> englishIndonesias = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitStr = line.split("\t");

                EnglishIndonesia englishIndonesia;

                englishIndonesia = new EnglishIndonesia(splitStr[0], splitStr[1]);
                englishIndonesias.add(englishIndonesia);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return englishIndonesias;
    }

    @Override
    public Observable<List<EnglishIndonesia>> fetchDatabaseEngInd() {
        return Observable.create(emitter -> {
            List<EnglishIndonesia> englishIndonesias = preLoadRawEngInd();
            openEngInd();
            beginTransactionEngInd();
            try {
                for (EnglishIndonesia model : englishIndonesias) {
                    insertTransactionEngInd(model);
                }

                setTransactionSuccessEngInd();
            } catch (Exception e) {
                // Jika gagal maka do nothing
                Log.e(TAG, "doInBackground: Exception");
            }

            endTransactionEngInd();
            closeEngInd();


            emitter.onNext(englishIndonesias);
            emitter.onComplete();
        });
    }

    @Override
    public AppKamusEngIndHelper openEngInd() throws SQLException {
        mSQLiteDatabase = mDataBaseHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void closeEngInd() {
        mDataBaseHelper.close();
    }

    @Override
    public Observable<List<EnglishIndonesia>> getDataBySearchWordEngInd(String word) {
        String result = "";
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME_ENG_IND,null, SEARCH_WORD_ENG_IND +" LIKE ?",
                new String[]{word},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        final ArrayList<EnglishIndonesia> arrayList = new ArrayList<>();
        EnglishIndonesia englishIndonesia;
        if (cursor.getCount() > 0) {
            do {
                englishIndonesia = new EnglishIndonesia();
                englishIndonesia.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishIndonesia.setEngSearch(cursor.getString(cursor.getColumnIndexOrThrow(SEARCH_WORD_ENG_IND)));
                englishIndonesia.setIndResult(cursor.getString(cursor.getColumnIndexOrThrow(RESULT_WORD_ENG_IND)));

                arrayList.add(englishIndonesia);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public Observable<List<EnglishIndonesia>> getSearchWordEngInd(String word) {
        String result = "";
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME_ENG_IND,null, SEARCH_WORD_ENG_IND +" LIKE ?",
                new String[]{word + "%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        final ArrayList<EnglishIndonesia> arrayList = new ArrayList<>();
        EnglishIndonesia englishIndonesia;
        if (cursor.getCount() > 0) {
            do {
                englishIndonesia = new EnglishIndonesia();
                englishIndonesia.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishIndonesia.setEngSearch(cursor.getString(cursor.getColumnIndexOrThrow(SEARCH_WORD_ENG_IND)));
                englishIndonesia.setIndResult(cursor.getString(cursor.getColumnIndexOrThrow(RESULT_WORD_ENG_IND)));

                arrayList.add(englishIndonesia);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public Observable<List<EnglishIndonesia>> getAllDataEngInd() {
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME_ENG_IND,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        final ArrayList<EnglishIndonesia> arrayList = new ArrayList<>();
        EnglishIndonesia englishIndonesia;
        if (cursor.getCount() > 0) {
            do {
                englishIndonesia = new EnglishIndonesia();
                englishIndonesia.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishIndonesia.setEngSearch(cursor.getString(cursor.getColumnIndexOrThrow(SEARCH_WORD_ENG_IND)));
                englishIndonesia.setIndResult(cursor.getString(cursor.getColumnIndexOrThrow(RESULT_WORD_ENG_IND)));


                arrayList.add(englishIndonesia);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    @Override
    public long insertEngInd(EnglishIndonesia englishIndonesia) {
        ContentValues initialValues =  new ContentValues();
        initialValues.put(SEARCH_WORD_ENG_IND, englishIndonesia.getEngSearch());
        initialValues.put(RESULT_WORD_ENG_IND, englishIndonesia.getIndResult());
        return mSQLiteDatabase.insert(TABLE_NAME_ENG_IND, null, initialValues);
    }

    @Override
    public void beginTransactionEngInd() {
        mSQLiteDatabase.beginTransaction();
    }

    @Override
    public void setTransactionSuccessEngInd() {
        mSQLiteDatabase.setTransactionSuccessful();
    }

    @Override
    public void endTransactionEngInd() {
        mSQLiteDatabase.endTransaction();
    }

    @Override
    public void insertTransactionEngInd(EnglishIndonesia englishIndonesia) {
        String sql = "INSERT INTO "+ TABLE_NAME_ENG_IND +" ("+ SEARCH_WORD_ENG_IND +", "+ RESULT_WORD_ENG_IND
                +") VALUES (?, ?)";
        SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
        stmt.bindString(1, englishIndonesia.getEngSearch());
        stmt.bindString(2, englishIndonesia.getIndResult());
        stmt.execute();
        stmt.clearBindings();
    }

    @Override
    public int updateEngInd(EnglishIndonesia englishIndonesia) {
        ContentValues args = new ContentValues();
        args.put(SEARCH_WORD_ENG_IND, englishIndonesia.getEngSearch());
        args.put(RESULT_WORD_ENG_IND, englishIndonesia.getIndResult());
        return mSQLiteDatabase.update(TABLE_NAME_ENG_IND, args, _ID + "= '" + englishIndonesia.getId() + "'", null);
    }

    @Override
    public int deleteEngInd(int id) {
        return mSQLiteDatabase.delete(TABLE_NAME_ENG_IND, _ID + " = '"+id+"'", null);
    }
}

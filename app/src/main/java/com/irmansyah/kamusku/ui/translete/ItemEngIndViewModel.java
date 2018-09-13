package com.irmansyah.kamusku.ui.translete;

import android.databinding.ObservableField;
import android.util.Log;

import com.irmansyah.kamusku.data.DataManager;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.ui.base.BaseViewModel;
import com.irmansyah.kamusku.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by irmansyah on 08/03/18.
 */

public class ItemEngIndViewModel {

    private static final String TAG = "ItemEngIndViewModel";

    private EnglishIndonesia mModel;

    public ObservableField<String> searchText = new ObservableField<>("");

    public ItemResultViewModelListener mListener;

    private int mPost;

    public ItemEngIndViewModel(int post, EnglishIndonesia model, ItemResultViewModelListener listener) {
        this.mPost = post;
        this.mModel = model;
        this.mListener = listener;

        searchText.set(mModel.getEngSearch());
    }

    public void onItemClicked() {
        mListener.onItemClick(mPost);
    }

    public interface ItemResultViewModelListener {
        void onItemClick(int post);
    }
}

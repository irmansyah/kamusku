package com.irmansyah.kamusku.ui.translete;

import android.databinding.ObservableField;

import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;

/**
 * Created by irmansyah on 09/03/18.
 */

public class ItemIndEngVIewModel {

    private static final String TAG = "ItemEngIndViewModel";

    private IndonesiaEnglish mModel;

    public ObservableField<String> searchText = new ObservableField<>("");

    public ItemResultViewModelListener mListener;

    private int mPost;

    public ItemIndEngVIewModel(int post, IndonesiaEnglish model, ItemResultViewModelListener listener) {
        this.mPost = post;
        this.mModel = model;
        this.mListener = listener;

        searchText.set(mModel.getIndSearch());
    }

    public void onItemClicked() {
        mListener.onItemClick(mPost);
    }

    public interface ItemResultViewModelListener {
        void onItemClick(int post);
    }
}

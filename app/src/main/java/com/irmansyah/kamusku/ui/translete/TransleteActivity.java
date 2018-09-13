package com.irmansyah.kamusku.ui.translete;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.irmansyah.kamusku.BR;
import com.irmansyah.kamusku.R;
import com.irmansyah.kamusku.data.model.db.EnglishIndonesia;
import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;
import com.irmansyah.kamusku.databinding.ActivityTransleteBinding;
import com.irmansyah.kamusku.ui.base.BaseActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class TransleteActivity extends BaseActivity<ActivityTransleteBinding, TransleteViewModel>
        implements TransleteNavigator, EngIndAdapter.ItemResultClickListener, IndEngAdapter.ItemResultClickListener {

    private static final String TAG = "TransleteActivity";

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    EngIndAdapter mEngIndAdapter;

    @Inject
    IndEngAdapter mIndEngAdapter;

    private TransleteViewModel mTransleteViewModel;

    ActivityTransleteBinding mActivityTransleteBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TransleteActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTransleteBinding = getViewDataBinding();
        mTransleteViewModel.setNavigator(this);
        setUp();
        setupRx();
        subscribeToLiveData();

        mEngIndAdapter.setItemResultClickListener(this);
        mIndEngAdapter.setItemResultClickListener(this);
    }

    private void setupRx() {
        mTransleteViewModel.getCompositeDisposable().add(
                RxTextView.textChanges(mActivityTransleteBinding.inputWordEdt)
                .debounce(1, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.toString().trim().length() >= 1)
                .subscribeOn(mTransleteViewModel.getSchedulerProvider().io())
                .observeOn(mTransleteViewModel.getSchedulerProvider().ui())
                .subscribe(charSequence -> {
                    mTransleteViewModel.setTypeWord(charSequence.toString());

                }, throwable -> {

                    Log.e(TAG, "onSearchClicked: ", throwable);

                }));
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mActivityTransleteBinding.engIndRecyclerView.setLayoutManager(mLayoutManager);
        mActivityTransleteBinding.engIndRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mActivityTransleteBinding.engIndRecyclerView.setAdapter(mEngIndAdapter);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        mActivityTransleteBinding.indEngRecyclerView.setLayoutManager(lm);
        mActivityTransleteBinding.indEngRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mActivityTransleteBinding.indEngRecyclerView.setAdapter(mIndEngAdapter);
    }

    private void subscribeToLiveData() {
        mTransleteViewModel.getEnglishIndonesiaListLiveData().observe(this, englishIndonesias ->
                mTransleteViewModel.addEnglishIndonesialistItemsToList(englishIndonesias));

        mTransleteViewModel.getIndonesiaEnglishListLiveData().observe(this, indonesiaEnglishes ->
                mTransleteViewModel.addIndonesiaEnglishlistItemsToList(indonesiaEnglishes));
    }


    @Override
    public TransleteViewModel getViewModel() {
        mTransleteViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TransleteViewModel.class);
        return mTransleteViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_translete;
    }

    @Override
    public void onItemResultEngIndClicked(List<EnglishIndonesia> englishIndonesias, int post) {
        String searchText = englishIndonesias.get(post).getEngSearch();
        String resultText = englishIndonesias.get(post).getIndResult();
        mTransleteViewModel.setResultText(searchText.toUpperCase() + " : \n" + resultText);
        mActivityTransleteBinding.inputWordEdt.setText("");
        mEngIndAdapter.clearItems();
        hideKeyboard();
    }

    @Override
    public void onItemResultIndEngClicked(List<IndonesiaEnglish> indonesiaEnglishes, int post) {
        String searchText = indonesiaEnglishes.get(post).getIndSearch();
        String resultText = indonesiaEnglishes.get(post).getEngResult();
        mTransleteViewModel.setResultText(searchText + " : \n" + resultText);
        mActivityTransleteBinding.inputWordEdt.setText("");
        mIndEngAdapter.clearItems();
        hideKeyboard();
    }

    @Override
    public void onSearchClicked() {
        String word = mActivityTransleteBinding.inputWordEdt.getText().toString().trim();
        if (!word.isEmpty()) {
            mTransleteViewModel.setSingleSearch(word.toUpperCase());
            mActivityTransleteBinding.inputWordEdt.setText("");
            mIndEngAdapter.clearItems();
            hideKeyboard();
        }
    }

    @Override
    public void wipeText() {
        mActivityTransleteBinding.inputWordEdt.setText("");
        mActivityTransleteBinding.resultEdt.setText("");
        mEngIndAdapter.clearItems();
        mIndEngAdapter.clearItems();
    }
}

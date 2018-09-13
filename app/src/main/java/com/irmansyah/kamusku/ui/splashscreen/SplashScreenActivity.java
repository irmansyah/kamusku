package com.irmansyah.kamusku.ui.splashscreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.irmansyah.kamusku.BR;
import com.irmansyah.kamusku.R;
import com.irmansyah.kamusku.databinding.ActivitySplashScreenBinding;
import com.irmansyah.kamusku.ui.base.BaseActivity;
import com.irmansyah.kamusku.ui.translete.TransleteActivity;

import javax.inject.Inject;

public class SplashScreenActivity extends BaseActivity<ActivitySplashScreenBinding, SplachScreenViewModel>
        implements SplashScreenNavigator {

    @Inject
    SplachScreenViewModel mSplachScreenViewModel;

    ActivitySplashScreenBinding mActivitySplashScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashScreenBinding = getViewDataBinding();
        mSplachScreenViewModel.setNavigator(this);
    }

    @Override
    public SplachScreenViewModel getViewModel() {
        return mSplachScreenViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void gotoTransleteActivity() {
        startActivity(TransleteActivity.getStartIntent(this));
        finish();
    }
}

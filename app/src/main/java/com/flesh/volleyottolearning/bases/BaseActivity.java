package com.flesh.volleyottolearning.bases;

import android.support.v7.app.ActionBarActivity;

import com.flesh.volleyottolearning.application.BusProvider;

/**
 * Created by afleshner on 2/27/2015.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }
}

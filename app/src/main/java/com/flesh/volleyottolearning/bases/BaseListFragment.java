package com.flesh.volleyottolearning.bases;

import android.support.v4.app.ListFragment;

import com.flesh.volleyottolearning.application.BusProvider;

/**
 * Created by afleshner on 2/27/2015.
 */
public class BaseListFragment extends ListFragment {

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }


}

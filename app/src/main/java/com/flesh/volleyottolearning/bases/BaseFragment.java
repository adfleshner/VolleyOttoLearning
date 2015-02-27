package com.flesh.volleyottolearning.bases;

import android.support.v4.app.Fragment;

import com.flesh.volleyottolearning.application.BusProvider;

/**
 * Created by afleshner on 2/27/2015.
 */
public class BaseFragment extends Fragment {
    @Override public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }


    @Override public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

}

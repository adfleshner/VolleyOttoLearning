package com.flesh.volleyottolearning.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.otto.Bus;

/**
 * Created by afleshner on 2/27/2015.
 */
public class LearningVolleyOttoApplication extends Application {

    private Bus mBus;
    private static LearningVolleyOttoApplication instance;
    private RequestQueue mRequestQueue;



    @Override
    public void onCreate() {
        super.onCreate();
        //Create An instance as soon as the application starts;
        instance = this;
    }

    public static synchronized LearningVolleyOttoApplication getInstance() {
        return instance;
    }

    /**
     * protected so that it can only be called by Classes in this package.
     *
     * @return
     */
    protected Bus getBus() {
        if (mBus == null) {
            mBus = new Bus();
        }
        return mBus;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    protected RequestQueue getRequestQueue() {
        // lazy initialize the request mRequestQueue, the mRequestQueue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }



}

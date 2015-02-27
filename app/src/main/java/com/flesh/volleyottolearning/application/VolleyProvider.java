package com.flesh.volleyottolearning.application;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.VolleyLog;

/**
 * Created by afleshner on 2/27/2015.
 */
public class VolleyProvider {

    /*
     * Log or request TAG
     */
    public static final String TAG = "VolleyProvider";

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public static <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        LearningVolleyOttoApplication.getInstance().getRequestQueue().add(req);
    }

    public static <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        LearningVolleyOttoApplication.getInstance().getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public static void cancelPendingRequests(Object tag) {
        LearningVolleyOttoApplication.getInstance().getRequestQueue().cancelAll(tag);
    }

    public static void invalidateCacheForTag(String tag) {
        LearningVolleyOttoApplication.getInstance().getRequestQueue().getCache().invalidate(tag, true);
    }

    public static void removeCacheForTag(String tag) {
        LearningVolleyOttoApplication.getInstance().getRequestQueue().getCache().remove(tag);
    }

}

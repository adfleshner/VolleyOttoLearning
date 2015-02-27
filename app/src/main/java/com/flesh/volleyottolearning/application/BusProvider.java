package com.flesh.volleyottolearning.application;

import com.squareup.otto.Bus;

/**
 * Created by afleshner on 2/27/2015.
 */
public class BusProvider {
    /**
     * Gets an Instance of the Otto EventBus
     *
     * @return LearningVolleyOttoApplication.getInstance().getBus()
     */
    public static Bus getInstance() {
        return LearningVolleyOttoApplication.getInstance().getBus();
    }
}

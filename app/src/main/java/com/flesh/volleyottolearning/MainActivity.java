package com.flesh.volleyottolearning;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.flesh.volleyottolearning.application.BusProvider;
import com.flesh.volleyottolearning.application.VolleyProvider;
import com.flesh.volleyottolearning.bases.BaseActivity;
import com.squareup.otto.Produce;

import java.util.Random;


public class MainActivity extends BaseActivity {
    public static final float DEFAULT_LAT = 40.440866f;
    public static final float DEFAULT_LON = -79.994085f;
    private static final float OFFSET = 0.1f;
    private static final Random RANDOM = new Random();

    private static float lastLatitude = DEFAULT_LAT;
    private static float lastLongitude = DEFAULT_LON;
    private static final String mURL = "http://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_history);

        findViewById(R.id.clear_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tell everyone to clear their location history.
                StringRequest req = new StringRequest(mURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        BusProvider.getInstance().post(new LocationClearEvent("Cleared"));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        BusProvider.getInstance().post(new LocationClearEvent("Error"));
                    }
                });
                VolleyProvider.addToRequestQueue(req, "REQ");

                // Post new location event for the default location.
                lastLatitude = DEFAULT_LAT;
                lastLongitude = DEFAULT_LON;
                BusProvider.getInstance().post(produceLocationEvent());
            }
        });

        findViewById(R.id.move_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLatitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                lastLongitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                BusProvider.getInstance().post(produceLocationEvent());
            }
        });
    }

    @Produce
    public LocationChangedEvent produceLocationEvent() {
        // Provide an initial value for location based on the last known position.
        return new LocationChangedEvent(lastLatitude, lastLongitude);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleyProvider.removeCacheForTag("REQ");
    }
}
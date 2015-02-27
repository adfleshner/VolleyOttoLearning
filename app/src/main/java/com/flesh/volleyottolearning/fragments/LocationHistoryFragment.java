package com.flesh.volleyottolearning.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.flesh.volleyottolearning.bases.BaseListFragment;
import com.flesh.volleyottolearning.LocationChangedEvent;
import com.flesh.volleyottolearning.LocationClearEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class LocationHistoryFragment extends BaseListFragment {
    private final List<String> locationEvents = new ArrayList<String>();
    private ArrayAdapter<String> adapter;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, locationEvents);
        setListAdapter(adapter);
    }

    @Subscribe
    public void onLocationChanged(LocationChangedEvent event) {
        locationEvents.add(0, event.toString());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onLocationCleared(LocationClearEvent event) {
        locationEvents.clear();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        Toast.makeText(getActivity(),event.message,Toast.LENGTH_SHORT).show();
    }
}

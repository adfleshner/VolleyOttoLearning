package com.flesh.volleyottolearning.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flesh.volleyottolearning.bases.BaseFragment;
import com.flesh.volleyottolearning.LocationChangedEvent;
import com.flesh.volleyottolearning.application.BusProvider;
import com.squareup.otto.Subscribe;

import java.net.URL;

import static android.widget.ImageView.ScaleType.CENTER_INSIDE;

/**
 * Created by afleshner on 2/27/2015.
 */
public class LocationMapFragment extends BaseFragment {
    private static final String URL =
            "https://maps.googleapis.com/maps/api/staticmap?sensor=false&size=400x400&zoom=13&center=%s,%s";
    private static DownloadTask downloadTask;

    private ImageView imageView;


    @Override
    public void onPause() {
        super.onPause();
        // Stop existing download, if it exists.
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageView = new ImageView(getActivity());
        imageView.setScaleType(CENTER_INSIDE);
        return imageView;
    }

    @Subscribe
    public void onLocationChanged(LocationChangedEvent event) {
        // Stop existing download, if it exists.
        if (downloadTask != null) {
            downloadTask.cancel(true);
        }

        // Trigger a background download of an image for the new location.
        downloadTask = new DownloadTask();
        downloadTask.execute(String.format(URL, event.lat, event.lon));
    }

    @Subscribe
    public void onImageAvailable(ImageAvailableEvent event) {
        if (imageView != null) {
            imageView.setImageDrawable(event.image);
        }
    }

    private static class ImageAvailableEvent {
        public final Drawable image;

        ImageAvailableEvent(Drawable image) {
            this.image = image;
        }
    }

    private static class DownloadTask extends AsyncTask<String, Void, Drawable> {
        @Override
        protected Drawable doInBackground(String... params) {
            try {
                return BitmapDrawable.createFromStream(new URL(params[0]).openStream(), "bitmap.jpg");
            } catch (Exception e) {
                Log.e("LocationMapFragment", "Unable to download image.", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (!isCancelled() && drawable != null) {
                BusProvider.getInstance().post(new ImageAvailableEvent(drawable));
            }
        }
    }
}
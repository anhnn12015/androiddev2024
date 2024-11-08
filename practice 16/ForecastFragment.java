package com.example.usthweather;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class ForecastFragment extends Fragment {

    private ImageView usthLogoImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        usthLogoImageView = view.findViewById(R.id.usth_logo);

        // Start the network request to download the USTH logo
        downloadUSTHLogo("https://www.usth.edu.vn/uploads/logo.png");

        return view;
    }

    private void downloadUSTHLogo(String url) {
        // Get the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        // Create an ImageRequest to download the image
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // Set the downloaded image in the ImageView
                        usthLogoImageView.setImageBitmap(response);
                        Toast.makeText(getContext(), "Logo downloaded successfully!", Toast.LENGTH_SHORT).show();
                    }
                },
                0, // Width (0 means use original size)
                0, // Height (0 means use original size)
                ImageView.ScaleType.CENTER, // Image scale type
                Bitmap.Config.RGB_565, // Bitmap configuration
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(getContext(), "Failed to download logo.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the request queue
        requestQueue.add(imageRequest);
    }
}

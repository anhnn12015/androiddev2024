package com.example.usthweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherFragment extends Fragment {

    private TextView weatherTempTextView;
    private TextView weatherDescriptionTextView;

    // Replace with your actual OpenWeather API key
    private final String API_KEY = "YOUR_API_KEY";
    private final String CITY = "Hanoi";
    private final String UNITS = "metric"; // Use "imperial" for Fahrenheit

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Initialize TextViews
        weatherTempTextView = view.findViewById(R.id.weather_temp);
        weatherDescriptionTextView = view.findViewById(R.id.weather_description);

        // Fetch weather data
        fetchWeatherData();

        return view;
    }

    private void fetchWeatherData() {
        // Construct the API URL
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY +
                "&units=" + UNITS + "&appid=" + API_KEY;

        // Get the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        // Create a JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the JSON data
                            JSONObject main = response.getJSONObject("main");
                            double temp = main.getDouble("temp");

                            JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                            String description = weather.getString("description");

                            // Update UI with the parsed data
                            weatherTempTextView.setText("Temperature: " + temp + "°C");
                            weatherDescriptionTextView.setText("Condition: " + description);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "JSON parsing error.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(getContext(), "Failed to get weather data.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }
}

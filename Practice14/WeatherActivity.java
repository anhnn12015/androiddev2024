package com.example.usthweather;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up ViewPager and TabLayout
        ViewPager viewPager = findViewById(R.id.viewpager);
        WeatherPagerAdapter adapter = new WeatherPagerAdapter(getSupportFragmentManager());

        // Adding instances of WeatherAndForecastFragment for different cities
        adapter.addFragment(WeatherAndForecastFragment.newInstance("Hanoi, Vietnam"));
        adapter.addFragment(WeatherAndForecastFragment.newInstance("Paris, France"));
        adapter.addFragment(WeatherAndForecastFragment.newInstance("Toulouse, France"));

        viewPager.setAdapter(adapter);

        // Set up TabLayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Set titles for each tab
        tabLayout.getTabAt(0).setText("Hanoi, Vietnam");
        tabLayout.getTabAt(1).setText("Paris, France");
        tabLayout.getTabAt(2).setText("Toulouse, France");
    }

    // Inflate the menu with the toolbar actions
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        return true;
    }

    // Handle toolbar actions (Refresh and Settings)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // Start the AsyncTask to simulate a network refresh
                new RefreshWeatherTask().execute();
                return true;

            case R.id.action_settings:
                // Start the PrefActivity when Settings is clicked
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Inner class for AsyncTask to simulate network request
    private class RefreshWeatherTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Show an initial toast message before the task starts
            Toast.makeText(WeatherActivity.this, "Refreshing weather data...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Simulate network delay
                Thread.sleep(2000); // 2 seconds delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Show a toast message after the refresh is complete
            Toast.makeText(WeatherActivity.this, "Weather data refreshed!", Toast.LENGTH_SHORT).show();
        }
    }
}

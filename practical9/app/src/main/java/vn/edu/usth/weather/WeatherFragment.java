package vn.edu.usth.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment {

    private TextView temperature;
    private TextView condition;
    private TextView location;
    private ImageView weatherIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        temperature = view.findViewById(R.id.weather_temperature);
        condition = view.findViewById(R.id.weather_condition);
        location = view.findViewById(R.id.location);
        weatherIcon = view.findViewById(R.id.weather_icon);

        // Giả sử bạn có dữ liệu từ API hoặc local, bạn có thể cập nhật nó như sau:
        updateWeather("12C", "Cloudy", "Paris");

        return view;
    }

    public void updateWeather(String temp, String cond, String loc) {
        temperature.setText(temp);
        condition.setText(cond);
        location.setText(loc);
        // Thay đổi biểu tượng dựa vào điều kiện thời tiết
        weatherIcon.setImageResource(R.drawable.weather);
    }
}


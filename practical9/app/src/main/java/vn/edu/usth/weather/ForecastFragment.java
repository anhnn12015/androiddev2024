package vn.edu.usth.weather;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ForecastFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Tạo một LinearLayout
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        // Tạo TextView cho ngày
        TextView textView = new TextView(getActivity());
        textView.setText("Thursday");
        textView.setTextSize(20);
        layout.addView(textView);

        // Tạo ImageView cho icon thời tiết
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.weather); // Tham chiếu tới ảnh bạn đã lưu trong drawable
        layout.addView(imageView);

        return layout;
    }
}

package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HanoiFragment(); // Hà Nội
            case 1:
                return new ParisFragment(); // Paris
            case 2:
                return new ToulouseFragment(); // Toulouse
            default:
                return new HanoiFragment();
        }
    }

    @Override
    public int getCount() {
        return 3; // Số lượng tab
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HANOI, VIETNAM";
            case 1:
                return "PARIS, FRANCE";
            case 2:
                return "TOULOUSE, FR";
            default:
                return null;
        }
    }
}


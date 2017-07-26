package skyblue.android.skyhotelapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 2017-07-26.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }


    @Override
    public Fragment getItem(int position) {
       switch (position){

           case 0:
               HotelInfoActivity hotelInfoActivity = new HotelInfoActivity();
               return hotelInfoActivity;
           case 1:
               CustomerActivity customerActivity = new CustomerActivity();
               return customerActivity;
           default: return null;
       }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

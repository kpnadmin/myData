package com.android.viewpagerimageslider04;

import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int image[] = null;

    public MyPagerAdapter(android.support.v4.app.FragmentManager fm, int  image[]) {
        super(fm);
        this.image = image;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new ImageFragment().newInstance(image[position]);
    }

    @Override
    public int getCount() {
        return image.length;
    }
}
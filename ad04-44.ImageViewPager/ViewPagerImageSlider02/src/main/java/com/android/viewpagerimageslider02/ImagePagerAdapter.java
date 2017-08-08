package com.android.viewpagerimageslider02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context = null;
    private LayoutInflater layoutInflater = null;
    private File[] resources = null;

    public ImagePagerAdapter(Context context, File [] files) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resources = files;
    }

    @Override
    public int getCount() {
        return this.resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = null;

        try {
            itemView = this.layoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

            FileInputStream in = new FileInputStream(this.resources[position]);
            BufferedInputStream buf = new BufferedInputStream(in);
            byte[] bMapArray= new byte[buf.available()];
            buf.read(bMapArray);
            Bitmap bmap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
            imageView.setImageBitmap(bmap);

            container.addView(itemView, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

package com.android.viewpagerimageslider04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class ImageFragment extends android.support.v4.app.Fragment{

    //app 버전으로 받으면 자동으로 에러제거
    public static ImageFragment newInstance(int imageUrl) {

        Bundle bundle = new Bundle();
        bundle.putInt("imageUrl", imageUrl);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.image_fragment,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_imageview);

        Bundle bundle = getArguments();
        imageView.setImageResource(bundle.getInt("imageUrl"));

        return view;
    }
}

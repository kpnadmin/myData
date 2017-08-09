package com.android.customloading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

/**
 * Created by Administrator on 2017-08-09.
 */

public class CustomLoading extends Dialog{


    public CustomLoading(@NonNull Activity activity) {
        super(activity);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //다이얼 로그 제목을 삭제하자
        setContentView(R.layout.custom_loading_bar);



    }
}

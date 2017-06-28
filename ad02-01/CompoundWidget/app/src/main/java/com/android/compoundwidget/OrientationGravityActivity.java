package com.android.compoundwidget;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class OrientationGravityActivity extends AppCompatActivity {
        // 위젯 선언
    private RadioGroup rgpDirection = null;
    private RadioGroup rgpGravity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_gravity);

        //위젯 찾기
        rgpDirection = (RadioGroup) findViewById(R.id.rgpDirection);
        // 핸들러 설정
        rgpDirection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (group == rgpDirection) {
                    if (checkedId == R.id.radiobtn_horizontal) {
                        rgpDirection.setOrientation(LinearLayout.HORIZONTAL);
                    } else {
                        rgpDirection.setOrientation(LinearLayout.VERTICAL);
                    }
                }
            }
        });

        rgpGravity = (RadioGroup) findViewById(R.id.rgpGravity);
        //
        rgpGravity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (group == rgpGravity) {
                    if (checkedId == R.id.radiobtn_left) {
                        rgpGravity.setGravity(Gravity.LEFT);
                    }else if (checkedId == R.id.radiobtn_middle) {
                        rgpGravity.setGravity(Gravity.CENTER);
                    } else {
                        rgpGravity.setGravity(Gravity.RIGHT);
                    }
                }
            }
        });



    }
}

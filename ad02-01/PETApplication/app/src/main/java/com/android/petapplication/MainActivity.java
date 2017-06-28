package com.android.petapplication;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 위젯 선언
    private RadioGroup rgp_animal = null;
    private CheckBox chk_start ;
    private LinearLayout line_pick_animal;
    private Button btn_animals;
    private ImageView img_animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 찾기
        rgp_animal = (RadioGroup) findViewById(R.id.rgp_animal);
        chk_start = (CheckBox) findViewById(R.id.chk_start);
        line_pick_animal = (LinearLayout) findViewById(R.id.line_pick_animal);
        btn_animals = (Button) findViewById(R.id.btn_animals);
        img_animals = (ImageView) findViewById(R.id.img_animals);

        chk_start.setChecked(false);


        // 핸들러 설정
        chk_start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView == chk_start) {
                    if (isChecked) {
                        line_pick_animal.setVisibility(View.VISIBLE);
                    } else {
                        line_pick_animal.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        //

        btn_animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(rgp_animal.getCheckedRadioButtonId()){
                    case R.id.radiobtn_dog:
                        img_animals.setImageResource(R.drawable.dog1);
                        img_animals.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radiobtn_cat:
                        img_animals.setImageResource(R.drawable.cat1);
                        img_animals.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radiobtn_rabbit:
                        img_animals.setImageResource(R.drawable.rabbit1);
                        img_animals.setVisibility(View.VISIBLE);
                        break;
                        default:
                            Toast.makeText(getApplicationContext(), "동물 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                            img_animals.setVisibility(View.INVISIBLE);
                }
            }
        });



      /*  btn_animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgp_animal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (group == rgp_animal) {
                            if (checkedId == R.id.radiobtn_dog) {
                                img_animals.setImageResource(R.drawable.dog1);
                            }else if (checkedId == R.id.radiobtn_cat) {
                                img_animals.setImageResource(R.drawable.cat1);
                            } else {
                                img_animals.setImageResource(R.drawable.rabbit1);
                            }
                        }
                    }
                });


            }
        });*/



    }
}

package com.android.moviestartactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
                     private final int RES_OK = 0;
                    private ImageView res_img_1 ;
                    private  TextView res_txt_1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표 결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imgName = intent.getStringArrayExtra("imgName");
        //ImageView[] img_view = (ImageView[]) intent.getExtras().get("imgMain");

        TextView tv[] = new TextView[imgName.length];
        RatingBar rbar[] = new RatingBar[imgName.length];

        res_img_1 = (ImageView) findViewById(R.id.res_img_1);
        res_txt_1 = (TextView) findViewById(R.id.res_txt_1);

       // img_view[0].setId(R.id.img_1);




        Integer tvID[] = {

               R.id.tv1,
               R.id.tv2,
               R.id.tv3,
               R.id.tv4,
               R.id.tv5,
               R.id.tv6,
               R.id.tv7,
               R.id.tv8,
               R.id.tv9

        };

        Integer rbarID[] = {
                R.id.rating_1  ,
                R.id.rating_2  ,
                R.id.rating_3  ,
                R.id.rating_4  ,
                R.id.rating_5  ,
                R.id.rating_6  ,
                R.id.rating_7  ,
                R.id.rating_8  ,
                R.id.rating_9

        };


        Integer Img_res[] = {

                    R.id.img_01  ,
                    R.id.img_02  ,
                    R.id.img_03  ,
                    R.id.img_04  ,
                    R.id.img_05  ,
                    R.id.img_06  ,
                    R.id.img_07  ,
                    R.id.img_08  ,
                    R.id.img_09


        };


        for(int i = 0 ; i < voteResult.length; i++){
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        for(int i = 0 ; i < voteResult.length; i++){


        }





        for(int i = 0; i<voteResult.length; i++){
            tv[i].setText(imgName[i]);
            rbar[i].setRating((float) voteResult[i]);
        }

        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*setResult(RES_OK, intent);*/
                finish();
            }
        });
        int idx = max(voteResult) ;

        for(int i = 0; i<voteResult.length; i++){

            if(voteResult[i] ==  idx){
                int p = Img_res[i];
                res_txt_1.setText(imgName[i]);
                //res_img_1.setImageResource(p);
                //res_img_1.setImageResource(Img_res[i]);
                break;
            }
        }




    }

    public static int max(int n[]) {
        int max = n[0];

        for (int i = 1; i < n.length; i++)
            if (n[i] > max) max = n[i];

        return max;
    }


    public static int min(int n[]) {
        int min = n[0];

        for (int i = 1; i < n.length; i++)
            if (n[i] < min) min = n[i];

        return min;
    }


}

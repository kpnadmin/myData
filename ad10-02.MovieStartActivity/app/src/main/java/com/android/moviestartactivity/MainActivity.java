package com.android.moviestartactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_1   ;
    private ImageView img_2   ;
    private ImageView img_3   ;
    private ImageView img_4   ;
    private ImageView img_5   ;
    private ImageView img_6   ;
    private ImageView img_7   ;
    private ImageView img_8   ;
    private ImageView img_9   ;

    private   int img_id_1;
    private   int img_id_2;
    private   int img_id_3;
    private   int img_id_4;
    private   int img_id_5;
    private   int img_id_6;
    private   int img_id_7;
    private   int img_id_8;
    private   int img_id_9;

    private Toast toast;

    private  String[] imgName;
    int voteCount[] = new int[9];

    private Button btn_finish;

    private static int RES_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        for(int i = 0 ; i < 9 ; i++){
            voteCount[i] = 0;
        }


        final ImageView image[] = new ImageView[9];



        img_1 = (ImageView) findViewById(R.id.img_1);
        img_2 = (ImageView) findViewById(R.id.img_2);
        img_3 = (ImageView) findViewById(R.id.img_3);
        img_4 = (ImageView) findViewById(R.id.img_4);
        img_5 = (ImageView) findViewById(R.id.img_5);
        img_6 = (ImageView) findViewById(R.id.img_6);
        img_7 = (ImageView) findViewById(R.id.img_7);
        img_8 = (ImageView) findViewById(R.id.img_8);
        img_9 = (ImageView) findViewById(R.id.img_9);


        image[0] = (ImageView) findViewById(R.id.img_1);
        image[1] = (ImageView) findViewById(R.id.img_2);
        image[2] = (ImageView) findViewById(R.id.img_3);
        image[3] = (ImageView) findViewById(R.id.img_4);
        image[4] = (ImageView) findViewById(R.id.img_5);
        image[5] = (ImageView) findViewById(R.id.img_6);
        image[6] = (ImageView) findViewById(R.id.img_7);
        image[7] = (ImageView) findViewById(R.id.img_8);
        image[8] = (ImageView) findViewById(R.id.img_9);



        img_1.setOnClickListener(this);
        img_2.setOnClickListener(this);
        img_3.setOnClickListener(this);
        img_4.setOnClickListener(this);
        img_5.setOnClickListener(this);
        img_6.setOnClickListener(this);
        img_7.setOnClickListener(this);
        img_8.setOnClickListener(this);
        img_9.setOnClickListener(this);

        imgName = new String[]{"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르앙", "잠자는 소녀"
                , "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들 ", "해변에서"
        };

        btn_finish = (Button) findViewById(R.id.btn_finish);


        btn_finish.setOnClickListener(this);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);

                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("imgName", imgName);
                //intent.putExtra("imgMain", image);


                startActivityForResult(intent,RES_CODE);


            }
        });





    }
    @Override
    public void onClick(View v){



        switch(v.getId()){

            case R.id.img_1 :
                voteCount[0] ++;
                if(toast != null)  toast.cancel();
                toast =   Toast.makeText(MainActivity.this, imgName[0]+" : 총 "+voteCount[0]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_2 :
                voteCount[1] ++;
                if(toast != null)  toast.cancel();
                toast =  Toast.makeText(MainActivity.this, imgName[1]+" : 총 "+voteCount[1]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_3 :
                voteCount[2] ++;
                if(toast != null)  toast.cancel();
                toast =  Toast.makeText(MainActivity.this, imgName[2]+" : 총 "+voteCount[2]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_4 :
                voteCount[3] ++;
                if(toast != null)  toast.cancel();
                toast =  Toast.makeText(MainActivity.this, imgName[3]+" : 총 "+voteCount[3]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_5 :
                voteCount[4] ++;
                if(toast != null)  toast.cancel();
                toast =  Toast.makeText(MainActivity.this, imgName[4]+" : 총 "+voteCount[4]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_6 :
                voteCount[5] ++;
                if(toast != null)  toast.cancel();
                toast = Toast.makeText(MainActivity.this, imgName[5]+" : 총 "+voteCount[6]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_7 :
                voteCount[6] ++;
                if(toast != null)  toast.cancel();
                toast = Toast.makeText(MainActivity.this, imgName[6]+" : 총 "+voteCount[6]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_8 :
                voteCount[7] ++;
                if(toast != null)  toast.cancel();
                toast =  Toast.makeText(MainActivity.this, imgName[7]+" : 총 "+voteCount[7]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.img_9 :
                voteCount[8] ++;
                if(toast != null)  toast.cancel();
                toast =  Toast.makeText(MainActivity.this, imgName[8]+" : 총 "+voteCount[8]+"표", Toast.LENGTH_SHORT);
                toast.show();
                break;




        }


    }


}

package skyblue.android.skyhotelapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button hotel_info, hotel_customer;
    private Button btn_roomInfo ,btn_reserve ,btn_facilities ,btn_location ,btn_phonecall;
    private EditText edit_cust_info, edit_cust_news, edit_cust_call, edit_cust_loc;
    private static int RES_CODE = 0;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int status = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Adding Toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("호텔 안내"));
        tabLayout.addTab(tabLayout.newTab().setText("고객 이용"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        );



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                status = tab.getPosition();
                if(status == 0 ) {
                   /* btn_location = (Button) findViewById(R.id.btn_location);
                    btn_location.setOnClickListener(MainActivity.this);*/
              /*      btn_location = (Button) findViewById(R.id.btn_location);
                    btn_location.setOnClickListener(MainActivity.this);
                    btn_roomInfo = (Button) findViewById(R.id.btn_roomInfo);
                    btn_reserve = (Button) findViewById(R.id.btn_reserve);
                    btn_reserve.setOnClickListener(MainActivity.this);
                    btn_facilities = (Button) findViewById(R.id.btn_facilities);
                    btn_phonecall = (Button) findViewById(R.id.btn_phonecall);*/


                }else if(status ==1){
                 /*   edit_cust_loc = (EditText) findViewById(R.id.edit_cust_loc);
                    edit_cust_loc.setOnClickListener(MainActivity.this);
                    edit_cust_info = (EditText) findViewById(R.id.edit_cust_info);
                    edit_cust_info.setOnClickListener(MainActivity.this);
*/



                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        /*btn_roomInfo = (Button) findViewById(R.id.btn_roomInfo);
        btn_reserve = (Button) findViewById(R.id.btn_reserve);
        btn_facilities = (Button) findViewById(R.id.btn_facilities);
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_phonecall = (Button) findViewById(R.id.btn_phonecall);
*/
       // btn_location.setOnClickListener(this);


   /*     hotel_info = (Button) findViewById(R.id.hotel_info);
        hotel_customer = (Button) findViewById(R.id.hotel_customer);*/
       /* btn_roomInfo = (Button) findViewById(R.id.btn_roomInfo);
        btn_reserve = (Button) findViewById(R.id.btn_reserve);
        btn_facilities = (Button) findViewById(R.id.btn_facilities);
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_phonecall = (Button) findViewById(R.id.btn_phonecall);*/



        //btn_location.setOnClickListener(this);


    }

  /*  @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_location :
                Intent intent = new Intent(getApplicationContext(),HotelLocActivity.class);
                startActivityForResult(intent,RES_CODE);
            case R.id.edit_cust_loc:
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.customer_dial);
                dialog.setTitle("오시는 길");
                TextView tv = (TextView) dialog.findViewById(R.id.text);
                tv.setText("Hello. This is a Custom Dialog !");
                ImageView iv = (ImageView) dialog.findViewById(R.id.img_hotel_findloc);
                iv.setImageResource(R.drawable.hotel_findloc);
                iv.setVisibility(View.VISIBLE);
                dialog.show();
            case R.id.edit_cust_info :
                intent = new Intent(getApplicationContext(),Hotel_Introduce.class);
                startActivityForResult(intent,RES_CODE);
            case R.id.btn_reserve:
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.reservedate_dial);
                dialog.setTitle("예약일정을 선택하세요.");
                dialog.show();
        }
    }*/

   /* @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_location :
                Intent intent = new Intent(getApplicationContext(),HotelLocActivity.class);
                startActivityForResult(intent,RES_CODE);
                default:return;
        }
    }*/

    @Override
    public void onClick(View v) {

        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BusProvider.getInstance().post(new ActivityResultEvent(requestCode, resultCode, data));
    }

    }


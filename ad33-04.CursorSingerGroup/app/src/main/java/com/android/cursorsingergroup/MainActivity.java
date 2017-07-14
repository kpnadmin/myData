package com.android.cursorsingergroup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

   private ListView listView      = null;
   private EditText edtName       = null;
   private EditText edtNumber     = null;

    private SQLiteDatabase db = null;
    CursorAdapterEx adapterEx = null;
    private View dialogView, toastView;
    private long _id = -1;

  /*  private RadioGroup rdo_order1 = null;
    private RadioGroup rdo_order2 = null;*/
    private RadioButton radio_name, radio_num, radio_asc, radio_desc;

    private String order_title = null;
    private String order_method = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView   =(ListView) findViewById(R.id.list_view);
        this.edtName    =(EditText) findViewById(R.id.edt_name);
        this.edtNumber  =(EditText) findViewById(R.id.edt_number);

        /*rdo_order1 = (RadioGroup) findViewById(R.id.rdo_order1);
        rdo_order2 = (RadioGroup) findViewById(R.id.rdo_order2);

         radio_name = (RadioButton) findViewById(R.id.radio_name);
        radio_num = (RadioButton) findViewById(R.id.radio_num);
        radio_asc = (RadioButton) findViewById(R.id.radio_asc);
        radio_desc = (RadioButton) findViewById(R.id.radio_desc);*/


        // 1. DBHelper 생성
        //MyDBHelper dbHelper = new MyDBHelper(this, "mydb", null, 1);
                                    MyDBHelper dbHelper = new MyDBHelper(this);
        // 2. DB open
         this.db = dbHelper.getWritableDatabase();


        // 3.1 테이블에서 데이터 가져오기
           Cursor cursor  =   db.rawQuery(" select * from groupTBL ", null);
        //db.rawQuery(" select * from groupTBL where _id = ? and gName = ? ", new String[] {"1" , "aaa" });



        // 3.2 Adapter 생성

         this.adapterEx = new CursorAdapterEx(getApplicationContext(),cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);


        // 3.3 listView 와 Adapter 연결
                 this.listView.setAdapter(this.adapterEx);

                this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // id == groupTBL_id 과 같다 .
                        Cursor cursor = db.rawQuery(" SELECT * FROM groupTBL where _id = ? ", new String[]{String.valueOf(id)});

                        // 커서위치 이동 : 0 번으로
                        cursor.moveToFirst();


                        String name = cursor.getString(cursor.getColumnIndex("gName"));
                        String number = cursor.getString(cursor.getColumnIndex("gNumber"));

                        edtName.setText(name);
                        edtNumber.setText(number);

                    //


                    }
                });

           this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   db.execSQL(" delete from groupTBL where _id= ?  ", new String[]{String.valueOf(id)});
                   // 커서위치 이동 : 0 번으로
                   edtName.setText("");
                   edtNumber.setText("");
                    // listview 새로고침
                   adapterEx.onContentChanged();
                  // Toast.makeText(getApplicationContext(), "삭제", Toast.LENGTH_SHORT);

                   return true;
               }
           });





/*
        rdo_order1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (group == rdo_order1) {
                    if (checkedId == R.id.radio_name) {
                        order_title = "gName";
                    }else if (checkedId == R.id.radio_num) {
                        order_title = "gNumber";
                    }else {

                    }
                }
            }
        });

        rdo_order2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (group == rdo_order2) {
                    if (checkedId == R.id.radio_asc) {
                        order_method = "asc";
                    }else if (checkedId == R.id.radio_desc) {
                        order_method = "desc";
                    }else {

                    }
                }
            }
        });*/

    }

    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btn_init:
                db.execSQL(" delete from groupTBL " );
                this.adapterEx.onContentChanged();
                break;
            case R.id.btn_insert:
                this.adapterEx.onContentChanged();
                break;
            case R.id.btn_select:
                final View dialogView = View.inflate(this, R.layout.dialog_search, null);

                final RadioGroup rdo_order1 = (RadioGroup) dialogView.findViewById(R.id.rdo_order1);
                final RadioGroup rdo_order2  = (RadioGroup) dialogView.findViewById(R.id.rdo_order2);

                // Preference 를 이용해서 설정 값 가져오기
                final SharedPreferences mPref = getSharedPreferences( "Setting", Context.MODE_PRIVATE );
                rdo_order1.check( mPref.getInt("rdo_order1", -1) );
                rdo_order2.check( mPref.getInt("rdo_order2" , -1) );

                AlertDialog.Builder dlg = new AlertDialog.Builder( this );
                dlg.setTitle("검색 조건을 입력하시오");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView( dialogView );
                dlg.setPositiveButton("검색", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Preference 를 이용해서 검색 옵션 값 저장하기
                        SharedPreferences.Editor prefEditor = mPref.edit();
                        prefEditor.putInt( "rdo_order1", rdo_order1.getCheckedRadioButtonId() );
                        prefEditor.putInt( "rdo_order2" , rdo_order2.getCheckedRadioButtonId() );
                        prefEditor.apply();  // Setting.xml 에 저장.


                        EditText name   = (EditText) dialogView.findViewById(R.id.dialog_name);
                        EditText number = (EditText) dialogView.findViewById(R.id.dialog_number);

                        String query = "";
                        query += String.format("   select * from groupTBL \r\n");
                        query += String.format("    where 1 = 1           \r\n");
                        if( !name.getText()  .toString().isEmpty() ) query += String.format("      and gName   = '%s'  \r\n", name.getText().toString()   );
                        if( !number.getText().toString().isEmpty() ) query += String.format("      and gNumber =  %s   \r\n", number.getText().toString() );

                        switch (rdo_order1.getCheckedRadioButtonId()){
                            case R.id.radio_name   :           query += String.format(" order by gName           \r\n"); break;
                            case R.id.radio_num :           query += String.format(" order by gNumber         \r\n"); break;
                        }

                        switch (rdo_order2.getCheckedRadioButtonId()){
                            case R.id.radio_asc :               query += String.format(" asc                      \r\n"); break;
                            case R.id.radio_desc:               query += String.format(" desc                     \r\n"); break;
                        }

                        Cursor cursor = db.rawQuery(query, null);
                        adapterEx.changeCursor( cursor );
                    }
                });
                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg.show();
                break;
            case R.id.btn_sort:
              /*  final View dialogView2 = View.inflate(this, R.layout.dialog_search, null);
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(this);
                dlg2.setIcon(R.drawable.ic_menu_allfriends);
                dlg2.setView(dialogView2);
                dlg2.setPositiveButton("정렬", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg2.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg2.show();*/


                break;
            case R.id.btn_update:
                db.execSQL(" update groupTBL set gName = ? , gNumber ? where id= ? ",
                        new String[] {edtName.getText().toString(), edtNumber.getText().toString(), String.valueOf(_id)}
                        );

                break;


        }


    }




    class CursorAdapterEx extends CursorAdapter {

        private Context context = null;
        private LayoutInflater inflator = null;
        private Handler handler = null;


        public CursorAdapterEx(Context context, Cursor c, int flags) {
            super(context, c, flags);
            this.context = context;
            this.inflator = LayoutInflater.from(context);
            this.handler = new Handler();
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = inflator.inflate(R.layout.listview_item, parent, false);



            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtName = (TextView) view.findViewById(R.id.txt_name);
            viewHolder.txtNumber = (TextView) view.findViewById(R.id.txt_number);
            view.setTag(viewHolder);


            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            ViewHolder viewHolder = (ViewHolder) view.getTag();

            viewHolder.txtName.setText(cursor.getString(cursor.getColumnIndex("gName")));
            viewHolder.txtNumber.setText(cursor.getString(cursor.getColumnIndex("gNumber")));

        }

        @Override
        protected void onContentChanged() {
            super.onContentChanged();
            Thread thread = new Thread(){

                @Override
                public void run() {
                    super.run();
                   final Cursor cursor = db.rawQuery(" select * from groupTBL ", null);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            changeCursor(cursor);
                        }
                    });

                }
            };
            thread.start();


        }
    }



}

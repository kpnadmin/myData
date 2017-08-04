package com.android.cursorsingergroup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private ListView listView  = null;
    private EditText edtName   = null;
    private EditText edtNumber = null;

    private SQLiteDatabase  db        = null;
    private CursorAdapterEx adapterEx = null;

    private long  _id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView  = (ListView) findViewById(R.id.list_view );
        this.edtName   = (EditText) findViewById(R.id.edt_name  );
        this.edtNumber = (EditText) findViewById(R.id.edt_number);

        /*
        // 1. DBHelper 생성
        // 2. DB open
        // 3.1 테이블에서 데이터 가져오기.
        // 3.2 Adapter 생성
        // 3.3 ListView와 Adatper 연결
        */

        // 1. DBHelper 생성
        MyDBHelper dbHelper = new MyDBHelper( getApplicationContext() );

        // 2. DB open
        this.db = dbHelper.getWritableDatabase();

        // 3.1 테이블에서 데이터 가져오기.
        Cursor cursor = db.rawQuery( " select * from groupTBL ", null );

        // 3.2 Adapter 생성
        this.adapterEx = new CursorAdapterEx(getApplicationContext(), cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER ) ;

        // 3.3 List@View와 Adatper 연결
        this.listView.setAdapter( this.adapterEx );


        // ItemClick 리스너 작성
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // id  ==  groupTBL._id
                Cursor cursor = db.rawQuery( " select * from groupTBL where _id=? ", new String[]{ String.valueOf(id) });

                // 커서 위치 이동 : 0번으로
                cursor.moveToFirst();

                String name   = cursor.getString( cursor.getColumnIndex( "gName"  ) );
                String number = cursor.getString( cursor.getColumnIndex( "gNumber") );

                edtName  .setText( name   );
                edtNumber.setText( number );

                //
                _id = id;
            }
        });

        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // id  ==  groupTBL._id
                db.execSQL( " delete from groupTBL where _id=? ", new String[]{ String.valueOf(id) });

                edtName  .setText( "" );
                edtNumber.setText( "" );

                // ListViw 새로고침
                adapterEx.onContentChanged();

                return true;
            }
        });
    }

    public void onClick(View view) {

        switch (view.getId() ) {
            case R.id.btn_init:
                db.execSQL(" delete from groupTBL " );
                this.adapterEx.onContentChanged();
                break;
            case R.id.btn_insert:
                this.adapterEx.onContentChanged();
                break;
            case R.id.btn_update:
                db.execSQL(" update groupTBL set gName=? , gNumber=? where _id=? ",
                        new String[]{edtName.getText().toString(), edtNumber.getText().toString(), String.valueOf( _id ) } );
                this.adapterEx.onContentChanged();
                break;
            case R.id.btn_select:
                // inflation
                final View dialogView = View.inflate(this, R.layout.dialog_search, null);

                final RadioGroup sortColumn = (RadioGroup) dialogView.findViewById(R.id.sort_column);
                final RadioGroup sortOrder  = (RadioGroup) dialogView.findViewById(R.id.sort_order);

                // Preference 를 이용해서 설정 값 가져오기
                final SharedPreferences mPref = getSharedPreferences( "Setting", Context.MODE_PRIVATE );
                sortColumn.check( mPref.getInt("sortColumn", -1) );
                sortOrder .check( mPref.getInt("sortOrder" , -1) );

                AlertDialog.Builder dlg = new AlertDialog.Builder( this );
                dlg.setTitle("검색 조건을 입력하시오");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView( dialogView );
                dlg.setPositiveButton("검색", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Preference 를 이용해서 검색 옵션 값 저장하기
                        SharedPreferences.Editor prefEditor = mPref.edit();
                        prefEditor.putInt( "sortColumn", sortColumn.getCheckedRadioButtonId() );
                        prefEditor.putInt( "sortOrder" , sortOrder.getCheckedRadioButtonId() );
                        prefEditor.apply();  // Setting.xml 에 저장.

                        EditText name   = (EditText) dialogView.findViewById(R.id.dialog_name);
                        EditText number = (EditText) dialogView.findViewById(R.id.dialog_number);

                                                                     String query = "";
                                                                     query += String.format("   select * from groupTBL \r\n");
                                                                     query += String.format("    where 1 = 1           \r\n");
                        if( !name.getText()  .toString().isEmpty() ) query += String.format("      and gName   = '%s'  \r\n", name.getText().toString()   );
                        if( !number.getText().toString().isEmpty() ) query += String.format("      and gNumber =  %s   \r\n", number.getText().toString() );

                        switch (sortColumn.getCheckedRadioButtonId()){
                            case R.id.sort_column_name   :           query += String.format(" order by gName           \r\n"); break;
                            case R.id.sort_column_number :           query += String.format(" order by gNumber         \r\n"); break;
                        }

                        switch (sortOrder.getCheckedRadioButtonId()){
                            case R.id.sort_order_asc :               query += String.format(" asc                      \r\n"); break;
                            case R.id.sort_order_desc:               query += String.format(" desc                     \r\n"); break;
                        }

                        Cursor cursor = db.rawQuery(query, null);
                        adapterEx.changeCursor( cursor );
                    }
                });
                dlg.setNegativeButton("닫기", null);
                dlg.show();
                break;
        }
    }

    class CursorAdapterEx extends CursorAdapter {

        private Context        context  = null;
        private LayoutInflater inflater = null;
        private Handler        handler  = null;

        private class ViewHolder {
            TextView txtName   = null;
            TextView txtNumber = null;

            public ViewHolder() {
                super();
            }
        }

        public CursorAdapterEx(Context context, Cursor c, int flags) {
            super(context, c, flags);

            this.context  = context;
            this.inflater = LayoutInflater.from(context);
            this.handler  = new Handler();
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = inflater.inflate(R.layout.listview_item, parent, false );

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtName   = (TextView) view.findViewById(R.id.txt_name  );
            viewHolder.txtNumber = (TextView) view.findViewById(R.id.txt_number);
            view.setTag( viewHolder );

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.txtName  .setText( cursor.getString( cursor.getColumnIndex("gName"  ) ) );
            viewHolder.txtNumber.setText( cursor.getString( cursor.getColumnIndex("gNumber") ) );
        }

        @Override
        protected void onContentChanged() {
            super.onContentChanged();

            Thread thread = new Thread(){
                @Override
                public void run() {
                    final Cursor cursor = db.rawQuery(" select * from groupTBL ", null);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changeCursor( cursor );
                        }
                    });

                }
            };
            thread.start();
        }
    }
}

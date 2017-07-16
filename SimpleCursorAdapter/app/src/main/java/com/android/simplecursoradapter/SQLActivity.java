package com.android.simplecursoradapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SQLActivity extends AppCompatActivity {
    DBHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        mDBHelper = new DBHelper(this, "Today.db", null, 1);
        SQLiteDatabase db = mDBHelper.getWritableDatabase(); //write 다할수있음
        db.execSQL("INSERT INTO today VALUES(null, '점심시간', '2011/07/15', '12:00', '여친과 식사', '1')");
        db.execSQL("INSERT INTO today VALUES(null, '저녁시간', '2011/07/16', '18:00', '여친과 식사', '2')");
        db.execSQL("INSERT INTO today VALUES(null, '아침시간', '2011/07/17', '08:00', '여친과 식사', '3')");

        Cursor cursor = db.rawQuery("SELECT _id,title, time, memo FROM today WHERE date = '2011/07/15'", null);
        // SELECT 에 맞는건만 리스트로 출력

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_view,
                cursor,
                new String[] {"title","time","memo"},
                new int[] {R.id.text1,R.id.text2,R.id.text3});

        ListView list = (ListView)this.findViewById(R.id.listview);
        list.setAdapter(adapter);
        mDBHelper.close();



    }
}

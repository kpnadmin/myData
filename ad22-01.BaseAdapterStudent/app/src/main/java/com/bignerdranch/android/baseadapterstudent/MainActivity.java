package com.bignerdranch.android.baseadapterstudent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView = null;
    BaseAdapterEx adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        // 1. 기본 데이터 생성
        List<ModelStudent> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ModelStudent student = new ModelStudent();
            student.setName("aaa " + i);
            student.setNumber(String.valueOf(i));
            student.setDept("ccc " + i);


            data.add(student);
        }

        // 2. 어뎁터를 생성
        adapter = new BaseAdapterEx(getApplicationContext(), data);

        // 3.머리 아이템 설정
        // !!! 주의 머릿/꼬리 아이템은 반드시 리스트의 setAdapter 전체 추가되어야 한다.
        View headerView = getLayoutInflater().inflate(R.layout.list_view_tail_layout, null);
        TextView headerTv = (TextView) headerView.findViewById(R.id.header_footer_text);
        headerTv.setText("리스트의 시작입니다.");
        listView.addHeaderView(headerView, null, true);

        // 4.꼬리 아이템 설정
        // !!! 주의 머릿/꼬리 아이템은 반드시 리스트의 setAdapter 전체 추가되어야 한다.
        View footerView = getLayoutInflater().inflate(R.layout.list_view_tail_layout, null);
        TextView footerTv = (TextView) footerView.findViewById(R.id.header_footer_text);
        footerTv.setText("로딩 중...");
        listView.addFooterView(footerView, null, false);

        // 5. 리스트뷰 아이템 핸들러 설정
        listView.setOnItemClickListener(new OnItemHandler());
        listView.setOnItemLongClickListener(new OnItemHandler());
        listView.setOnItemSelectedListener(new OnItemHandler());

        // 6. 리스트 아이템 구분선 설정
        listView.setDivider(new ColorDrawable(Color.GRAY));
        listView.setDividerHeight(3);

        // 7. 리스트뷰에 어뎁터 설정
        listView.setAdapter(adapter);





    }

    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            ModelStudent s = (ModelStudent) parent.getItemAtPosition(position);
            String msg = "Adapter Item Count : " + adapter.getCount() + "\n";
            msg += "ListView Item Count : " + listView.getCount() + "\n";
            msg += "Name : " + s.getName() + "\n";
            msg += "Number : " + s.getNumber() + "\n";
            msg += "Department : " + s.getDept() + "\n";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();


        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            adapter.remove((int) id);
            return false;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "onItemSelected Item : " + position + ", " + id, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(MainActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                EditText editName = (EditText) findViewById(R.id.edit_name);
                EditText editNum = (EditText) findViewById(R.id.edit_num);
                EditText editDept = (EditText) findViewById(R.id.edit_dept);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editNum.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(editDept.getWindowToken(), 0);

                ModelStudent student = new ModelStudent(editName.getText().toString(),
                        editNum.getText().toString(),
                        editDept.getText().toString()
                );
                adapter.insert(student, 0);
                editName.setText("");
                editNum.setText("");
                editDept.setText("");
                break;
            case R.id.btn_del:
                EditText edit_DelTime = (EditText) findViewById(R.id.edit_del_item);
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_DelTime.getWindowToken(), 0);

                if (!edit_DelTime.getText().toString().isEmpty()) {
                    int position = Integer.parseInt(edit_DelTime.getText().toString());
                    adapter.remove(position);

                }
                edit_DelTime.setText("");
                break;
            case R.id.btn_all_del:
                adapter.clear();
                break;
            case R.id.btn_sort:
                adapter.sort();
                break;
        }
    }
}

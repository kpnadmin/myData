package com.android.baseadapterex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BaseAdapterEx adapter = null;
    ArrayList<ModelStudent> mData =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 어댑터에서 사용할 데이터 설정
        mData = new ArrayList<ModelStudent>();
        for(int i = 0 ; i < 100 ; i++){
            ModelStudent student = new ModelStudent();
            student.setName("a" +i);
            student.setNumber("95000" +i);
            student.setDepartment("cop "+i);
            mData.add(student);
        }
        //2. 어댑터를 생성하고 데이터 설정
        adapter = new BaseAdapterEx(this, mData);
        // 3. 리스트뷰에 어댑터 설정
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_add:
                EditText name_edit = (EditText) findViewById(R.id.name_edit);
                EditText number_edit = (EditText) findViewById(R.id.number_edit);
                EditText department_edit = (EditText) findViewById(R.id.department_edit);

                ModelStudent addData = new ModelStudent();
                addData.setName(name_edit.getText().toString());
                addData.setNumber(number_edit.getText().toString());
                addData.setDepartment(department_edit.getText().toString());
                adapter.add(0, addData);
                break;
            case R.id.btn_del:
                EditText delItemIndexEt = (EditText) findViewById(R.id.edit_del);
                String str_idx =delItemIndexEt.getText().toString();
                //Integer index = Integer.parseInt(str_idx);
                adapter.delete(str_idx);
                break;
            case R.id.btn_clear:
                adapter.clear();
                break;
        }
    }

}

package com.android.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox           checkbox = null;
    private EditText           txtInteger = null;
    private EditText           txtString  = null;
    private SharedPreferences  pref = null;
        
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        checkbox = (CheckBox) findViewById( R.id.sound_on_off_chkbox );
        txtInteger = (EditText) findViewById(R.id.txt_integer);
        txtString  = (EditText) findViewById(R.id.txt_string);
        
        // 1. 공유 프레퍼런스 객체를 얻어온다.
        pref = getSharedPreferences( "Setting", Context.MODE_PRIVATE );
        
        // 2. 공유 프레퍼런스를 이용하여 사운드 on/off 설정 값을 얻어온다.
        boolean isSoundOn   = pref.getBoolean( "SOUND_SET"   , true );
        Integer text_number = pref.getInt    ( "TEXT_NUMBER" , -1   );
        String  text_string = pref.getString ( "TEXT_STRING" , ""   );

        // 3. 현재 사운드 설정 값을 체크 박스에 반영한다.
        checkbox.setChecked( isSoundOn );
        txtInteger.setText( text_number.toString() );
        txtString .setText( text_string );
    }

    public void onClick( View v ) {
        // 2. 공유 프레퍼런스에 사운드 설정 값을 저장한다.
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putBoolean( "SOUND_SET"  , checkbox.isChecked()                               );
        prefEditor.putInt    ( "TEXT_NUMBER", Integer.valueOf( txtInteger.getText().toString() ) );
        prefEditor.putString ( "TEXT_STRING", txtString.getText().toString()                     );
        prefEditor.apply();
    }
}
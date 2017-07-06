package com.android.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox mSoundChkbox = null;
    SharedPreferences mPref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundChkbox = (CheckBox) findViewById(R.id.sound_on_off_chkbox);
        // .1 공유 프리퍼런스 객체를 얻어온다 .
        mPref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        // 2. 공유 프리퍼런스 를 이용하여 사운드 on /off 설정값을 얻어온다 .
        boolean isSoundOn = mPref.getBoolean("SOUND_SET", true);

        // 3. 현재 사운드 설정값을 체크박스에 반영한다.
        mSoundChkbox.setChecked(isSoundOn);
        // 4. 공유 프리퍼런스 값이 변경되었을때 호출되는 리스너 등록

      /*  mPref.registerOnSharedPreferenceChangeListener(mPrefChangeListener);
*/

    }


    class PrefChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener{

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            boolean isSoundOn = sharedPreferences.getBoolean(key, true);
            String msg = key + "설정이" + isSoundOn + "로 변경";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }


    public void onClick(View v ){
        SharedPreferences.Editor prefEditor = mPref.edit();
        prefEditor.putBoolean("SOUND_SET", mSoundChkbox.isChecked());
        prefEditor.apply();
    }


}


package com.bignerdranch.android.googleaccount;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     private   TextView google_txt;
        private String possibleEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccountManager manager = AccountManager.get(this);
        Account[] accounts =  manager.getAccounts();
        final int count = accounts.length;
        Account account = null;

        for(int i=0;i<count;i++) {
            account = accounts[i];
            Log.d("HSGIL", "Account - name: " + account.name + ", type :" + account.type);
            possibleEmail = account.name;
            if(account.type.equals("com.google")){		//이러면 구글 계정 구분 가능

            }
        }

        google_txt = (TextView) findViewById(R.id.google_txt);

        google_txt.setText(possibleEmail);
    }
}

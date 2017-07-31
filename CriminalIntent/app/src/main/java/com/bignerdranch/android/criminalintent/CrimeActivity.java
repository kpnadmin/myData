package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {


    /*    public static final String EXTRA_CRIME_ID =
                "com.bignerdranch.android.criminalintent.crime_id";
    */
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context pakageContext, UUID crimeId){
        Intent intent = new Intent(pakageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        return new CrimeFragment();



    }


}

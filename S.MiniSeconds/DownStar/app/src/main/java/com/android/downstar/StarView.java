package com.android.downstar;

import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017-08-08.
 */

public class StarView {


    private StarGameView gameView;




    public StarView(StarGameView gameView){

        this.gameView = gameView;

        loadResources();
    }




    public void loadResources()
    {
        DisplayMetrics metrics = gameView.getMetrics();

    }

}

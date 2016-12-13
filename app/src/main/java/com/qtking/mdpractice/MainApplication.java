package com.qtking.mdpractice;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Think on 2016/12/9.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("MDPractise");
    }


}

package com.vitorjorge.englishexpertnotes.englishexpertnotes;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing Active Android
        ActiveAndroid.initialize(this);
    }
}

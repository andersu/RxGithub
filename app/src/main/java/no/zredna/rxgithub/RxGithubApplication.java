package no.zredna.rxgithub;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class RxGithubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}

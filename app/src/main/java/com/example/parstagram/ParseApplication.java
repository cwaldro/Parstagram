package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EVi235CIUb5cmEKTvvEEpPoEdp9jvIPRstIbGFKV")
                .clientKey("Zxe7vKrwXKFxQkVh9aJvNTd6GytocLYvdptVadDI")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

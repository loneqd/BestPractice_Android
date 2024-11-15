package com.lqd.androidpractice;

import android.util.Log;


import com.lqd.rn.RNApplication;

import java.util.List;

public class MainApplication extends RNApplication {

    private static String TAG = "LQDApplication";

    @Override
    public void onCreate() {
        super.onCreate();
//        Log.d(TAG, "onCreate");
//        Integer f1 = 100, f2 = 100, f3 = 200, f4 = 200;
//        Log.d(TAG, "" + (f1 == f2));
//        Log.d(TAG, "" + (f3 == f4));
//        ActivityManager.INSTANCE.check();


    }

//    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
//        @Override
//        public boolean getUseDeveloperSupport() {
//            return BuildConfig.DEBUG;
//        }
//
//        @Override
//        protected List<ReactPackage> getPackages() {
//            List<ReactPackage> packages = new PackageList(this).getPackages();
//            packages.add(new CustomToastPackage());
//            return packages;
//        }
//
//    };
//
//    @Override
//    public ReactNativeHost getReactNativeHost() {
//        return mReactNativeHost;
//    }
}

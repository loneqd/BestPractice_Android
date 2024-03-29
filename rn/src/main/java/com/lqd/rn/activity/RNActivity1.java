package com.lqd.rn.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;

//TODO 远程加载 ip:port/index.bundle?platform=android&dev=true&minify=false

/**
 * 使用继承的方式，需要设置Application
 */
public class RNActivity1 extends ReactActivity {


  // private ReactBundle mReactBundle;
  // private ReactBundleLoadManager mBundleManager;


    @Nullable
    @Override
    protected String getMainComponentName() {
        return "MyReactNativeApp";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {

        //暴露各种回调
        return new ReactActivityDelegate(this, getMainComponentName()) {

            @Override
            public String getMainComponentName() {
                return "MyReactNativeApp";
            }

            @Override
            protected void onPause() {
                super.onPause();
            }

            @Override
            protected void onResume() {
                super.onResume();
            }

            @Override
            protected void onDestroy() {
                super.onDestroy();
            }
        };
    }

    @Override
    public int checkPermission(String s, int i, int i1) {
        return 0;
    }

    @Override
    public int checkSelfPermission(String s) {
        return 0;
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String s) {
        return false;
    }

    // private class ReactActivityDelegateEx extends ReactActivityDelegate {
    //   private final Activity mActivity;

    //   public ReactActivityDelegateEx(Activity activity, @Nullable String mainComponentName) {
    //     super(activity, mainComponentName);
    //     mActivity = activity;
    //   }

    //   public ReactNativeHost getReactNativeHost() {
    //     return ((ReactApplication) mActivity.getApplication()).getReactNativeHost();
    //   }

    //   publlic Bundle getLaunchOptions() {
    //     return getReactNativeHost().getLaunchOptions();
    //   }

    //   protected ReactRootView createRootView() {
    //     return new ReactRootView(mActivity);
    //   }

    // }


}

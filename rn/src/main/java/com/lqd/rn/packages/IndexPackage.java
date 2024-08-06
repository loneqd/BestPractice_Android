package com.lqd.rn.packages;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.lqd.rn.manager.MyReactImageManager;
import com.lqd.rn.manager.MyTextViewManager;
import com.lqd.rn.manager.MyWebViewManager;
import com.lqd.rn.modules.IndexModule;

public class IndexPackage implements ReactPackage {
    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new IndexModule(reactContext));
        return modules;
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        List<ViewManager> modules = new ArrayList<>();
        modules.add(new MyReactImageManager(reactContext));
        modules.add(new MyTextViewManager(reactContext));
        modules.add(new MyWebViewManager(reactContext));
        return modules;
    }
}

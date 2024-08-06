package com.lqd.rn.manager;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class MyTextViewManager extends SimpleViewManager<TextView> {

    private ReactApplicationContext context;

    public MyTextViewManager(ReactApplicationContext context) {
        this.context = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "RCTTextView";
    }

    @NonNull
    @Override
    protected TextView createViewInstance(@NonNull ThemedReactContext reactContext) {
        TextView textView = new TextView(context);
        textView.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams lp = textView.getLayoutParams();
                lp.width = 100;
                lp.height = 100;
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WritableMap event = Arguments.createMap();
                event.putString("message", "MyMessage");
                //原生事件通知rn,我们可以在原生中处理点击事件，如点击TextView，我们直接弹出一个Toast，但是如何想要在rn端响应这个事件，那么可以通过下面这种方式
                reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onClick", event);
            }
        });
        return textView;
    }

    @Override
    public void onDropViewInstance(@NonNull TextView view) {
        super.onDropViewInstance(view);
    }

    @ReactProp(name = "text")
    public void setText(TextView view, String text) {
        Log.e("RCTTextView", "text is " + text);
        view.setText(text);
        view.setTextSize(10);
        Log.e("RCTTextView", "layoutParams is " + view.getLayoutParams());
    }
}

package com.lqd.rn.manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.image.ReactImageView;

import java.util.Map;


/**
 * 原生UI组件
 */
public class MyReactImageManager extends SimpleViewManager<ReactImageView> {

    public static final String REACT_CLASS = "RCTImageView";

    private final ReactApplicationContext mCallerContext;

    public MyReactImageManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected ReactImageView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ReactImageView imageView = new ReactImageView(reactContext, Fresco.newDraweeControllerBuilder(), null, mCallerContext);
        imageView.setOnClickListener(v -> {
            WritableMap data = Arguments.createMap();
            data.putString("msg", "点击了图片");
            mCallerContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onClick", data);
        });
        return imageView;
    }

    @ReactProp(name = "src")
    public void setSrc(ReactImageView view, ReadableArray sources) {
        view.setSource(sources);
    }

    @ReactProp(name = "borderRadius", defaultFloat = 0f)
    public void setBorderRadius(ReactImageView view, float borderRadius) {
        view.setBorderRadius(borderRadius);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(ReactImageView view, String resizeMode) {
        view.setScaleType(ImageResizeMode.toScaleType(resizeMode));
    }


    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return super.getCommandsMap();
    }

    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("topChange",
                        MapBuilder.of("phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onChange")))
                .build();
    }

    public static class UIManagerModule extends ReactContextBaseJavaModule {


        public UIManagerModule(@NonNull ReactApplicationContext reactContext) {
            super(reactContext);

        }


        /**
         * 发送消息给RN
         * @param reactContext
         * @param eventName
         * @param params
         */
        private void sendEvent(ReactContext reactContext,
                               String eventName,
                               @Nullable WritableMap params) {
            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }

        private static final String E_LAYOUT_ERROR = "E_LAYOUT_ERROR";

        @ReactMethod
        public void measureLayout(
                int tag,
                int ancestorTag,
                Promise promise) {
            try {
    //            measureLayout(tag, ancestorTag, mMeasureBuffer);
    //            WritableMap map = Arguments.createMap();
    //            map.putDouble("relativeX ", PixelUtil.toDIPFromPixel(mMeasureBuffer[0]));
    //            map.putDouble("relativeY", PixelUtil.toDIPFromPixel(mMeasureBuffer[1]));
    //            map.putDouble("width", PixelUtil.toDIPFromPixel(mMeasureBuffer[2]));
    //            map.putDouble("height", PixelUtil.toDIPFromPixel(mMeasureBuffer[3]));

    //            promise.resolve(map);
            } catch (IllegalViewOperationException e) {
                promise.reject(E_LAYOUT_ERROR, e);
            }
        }


        @NonNull
        @Override
        public String getName() {
            return null;
        }
    }
}

package com.lqd.androidpractice;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.lqd.androidpractice.activity.IntentExampleActivity;
import com.lqd.androidpractice.activity.UIExampleActivity;
import com.lqd.androidpractice.lifecycle.LaunchActivity1;
import com.lqd.androidpractice.services.HelloService;
import com.lqd.androidpractice.view.RadiusProgressBar;
import com.lqd.base.activity.BaseActivity;
import com.lqd.rn.activity.MyReactActivity;
import com.lqd.rn.activity.RNActivity1;
import com.lqd.utils.AudioRecordUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private static final int UPDATE_UI = 1;

    private static final int START = 2;
    private static final int STOP = 3;

    private Button btn1;

    private int percent = 1;

    RadiusProgressBar progressBar;

    AudioRecordUtil audioRecordUtil;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_UI) {

            } else if (msg.what == START) {
                //                requestAudioPermissions(MainActivity.this);
            } else if (msg.what == STOP) {
                if (audioRecordUtil != null) {
                    audioRecordUtil.stopRecord();
                    audioRecordUtil = null;
                }
            }
        }
    };

    private void stopRecord() {
        if (audioRecordUtil != null) {
            audioRecordUtil.stopRecord();
            audioRecordUtil = null;
        }
    }

    private void startRecordAudio() {
        if (audioRecordUtil == null) {
            audioRecordUtil = new AudioRecordUtil();
        }
        audioRecordUtil.startRecord(MainActivity.this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String ua = System.getProperty("http.agent");
        //        TestView xxx = new TestView(getApplicationContext());
        //        xxx.invalidate();
        Log.d(TAG, "onCreate" + ua);
        setContentView(R.layout.activity_main);

        ((ImageView) findViewById(R.id.v_1)).setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgressBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_load_progress));

        findViewById(R.id.handler1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = START;
                handler.sendMessage(message);
                //                startRecordAudio();
            }
        });

        btn1 = findViewById(R.id.start);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "jaja", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecord();
                //                Message message = new Message();
                //                message.what = STOP;
                //                handler.sendMessage(message);
                //                handler.post(new Runnable() {
                //
                //                    @Override
                //                    public void run() {
                //                        btn1.setText("happy");
                //                    }
                //                });
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //测试hprose
                Intent intent = new Intent(MainActivity.this, LaunchActivity1.class);
                MainActivity.this.startActivity(intent);

            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                play();
                test();
            }
        });

        Log.d(TAG, "isEmulator=" + com.lqd.utils.EmulatorUtil.getInstance().readSysProperty(this, null));

        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        Log.d(TAG, "====" + (f1 == f2));
        Log.d(TAG, "====" + (f3 == f4));

        findViewById(R.id.btnKotlin).setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, KotlinTestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.this.startActivity(intent);

        });

        findViewById(R.id.btnReactNative).setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, MyReactActivity.class);
            MainActivity.this.startActivity(intent);
        });
        findViewById(R.id.btnReactNative2).setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, RNActivity1.class);
            MainActivity.this.startActivity(intent);
        });

        findViewById(R.id.handlerActivityBtn).setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, com.lqd.androidpractice.handler.HandlerActivity.class);
            MainActivity.this.startActivity(intent);
        });

        findViewById(R.id.uiBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UIExampleActivity.class);
            MainActivity.this.startActivity(intent);
        });

        findViewById(R.id.carouseBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, com.lqd.androidpractice.activity.CarouseActivity.class);
            MainActivity.this.startActivity(intent);
        });
        findViewById(R.id.composeBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, com.lqd.androidpractice.activity.ComposeExample1Activity.class);
            MainActivity.this.startActivity(intent);
        });

        findViewById(R.id.composeBtn2).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, com.lqd.androidpractice.activity.ComposeExample1Activity.class);
            MainActivity.this.startActivity(intent);
        });

        findViewById(R.id.intentBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IntentExampleActivity.class);
            MainActivity.this.startActivity(intent);
        });

        findViewById(R.id.serviceBtn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HelloService.class);
            MainActivity.this.startService(intent);
        });


    }

    private void interval() {
        //        Observable.interval(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
        //                        .subscribe(new Observer<Long>() {
        //                            @Override
        //                            public void onSubscribe(@NotNull Disposable d) {
        //
        //                            }
        //
        //                            @Override
        //                            public void onNext(@NotNull Long aLong) {
        //                                progressBar.setProgress(percent);
        //                                percent += 1;
        //                            }
        //
        //                            @Override
        //                            public void onError(@NotNull Throwable e) {
        //
        //                            }
        //
        //                            @Override
        //                            public void onComplete() {
        //
        //                            }
        //                        });
    }


    private View view;

    private void play() {
        if (view == null) {
            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_world_banner_gift_game, null);
            ((TextView) view.findViewById(R.id.tv_title)).setText("hahahahahahahahahahahaha");
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            FrameLayout currentView = findViewById(android.R.id.content);
            currentView.addView(view);
        }

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = view.getMeasuredWidth();
        layoutParams.height = 150;
        layoutParams.topMargin = 300;
        view.setLayoutParams(layoutParams);
        view.requestLayout();
        //        view.setTranslationX(50);


        int viewWidth = view.getLayoutParams().width;
        int startTranslation = 0;
        int endTranslation = getResources().getDisplayMetrics().widthPixels;

        ObjectAnimator stageOneAnimator = ObjectAnimator.ofFloat(view, "translationX", endTranslation, startTranslation);
        stageOneAnimator.setDuration(3000);
        stageOneAnimator.start();
        //
        //        stageOneAnimator.addListener(new AnimatorListenerAdapter() {
        //            @Override
        //            public void onAnimationEnd(Animator animation, boolean isReverse) {
        //                Log.d("####", "enddddd");
        //            }
        //        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "" + newConfig.orientation + "----" + getWindowManager().getDefaultDisplay());

    }

    public void test() {


        List<String> SUBJECTS = new ArrayList<>();
        SUBJECTS.add("Java");
        SUBJECTS.add("Db");
        SUBJECTS.add("Spring");
        Flowable.fromIterable(SUBJECTS).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return "1234 " + s;
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });

        // 使用操作符
        //        Flowable.create(e -> {e.onNext("111");
        //        e.onComplete();}, BackpressureStrategy.BUFFER).subscribe(new FlowableSubscriber<Object>() {
        //            @Override
        //            public void onSubscribe(@NonNull Subscription s) {
        //                Log.d(TAG, "onSubscribe " + Thread.currentThread().getName());
        //            }
        //
        //            @Override
        //            public void onNext(Object o) {
        //                Log.d(TAG, "onNext " + Thread.currentThread().getName());
        //            }
        //
        //            @Override
        //            public void onError(Throwable t) {
        //                Log.d(TAG, "onError " + Thread.currentThread().getName());
        //            }
        //
        //            @Override
        //            public void onComplete() {
        //                Log.d(TAG, "onComplete " + Thread.currentThread().getName());
        //            }
        //        });
        //                .map(new Function<String, Integer>() {
        //                    @Override
        //                    public Integer apply(@NonNull String s) throws Exception {
        //                        return 1;
        //                    }
        //                })
        //                .subscribe(new FlowableSubscriber<Integer>() {
        //                    @Override
        //                    public void onSubscribe(@NonNull Subscription s) {
        //                        Log.d(TAG, "onSubscribe " + Thread.currentThread().getName());
        //                    }
        //
        //                    @Override
        //                    public void onNext(Integer integer) {
        //                        Log.d(TAG, "onNext " + Thread.currentThread().getName() + "    " + integer);
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable t) {
        //                        Log.e(TAG, "onError");
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //                        Log.d(TAG, "onComplete " + Thread.currentThread().getName());
        //                    }
        //                });

        //        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        //            @Override
        //            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                emitter.onNext(1);
        //                emitter.onNext(2);
        //                emitter.onNext(3);
        //                emitter.onComplete();
        //            }
        //        });
        //
        //        Observer<Integer> observer = new Observer<Integer>() {
        //            @Override
        //            public void onSubscribe(Disposable d) {
        //                Log.d(TAG, "subscribe");
        //            }
        //
        //            @Override
        //            public void onNext(Integer integer) {
        //                Log.d(TAG, "" + integer);
        //            }
        //
        //            @Override
        //            public void onError(Throwable e) {
        //                Log.d(TAG, "" + "error");
        //            }
        //
        //            @Override
        //            public void onComplete() {
        //                Log.d(TAG, "complete");
        //            }
        //        };

        //        observable.subscribe(observer);


        //        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
        //            @Override
        //            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                emitter.onNext(1);
        //                Log.d(TAG, Thread.currentThread().getName());
        //                Log.d(TAG, "emit 1");
        //            }
        //        });
        //
        //        Consumer<Integer> consumer = new Consumer<Integer>() {
        //            @Override
        //            public void accept(Integer integer) throws Exception {
        //                Log.d(TAG, "onNext " + integer);
        //                Log.d(TAG, Thread.currentThread().getName());
        //            }
        //        };
        //
        ////        observable1.subscribeOn(Schedulers.newThread())
        ////                .observeOn(AndroidSchedulers.mainThread())
        ////                .subscribe(consumer);
        //
        ////        Integer[] items = {1, 2, 3, 4, 5};
        ////        Observable myObservable = Observable.fromArray(items);
        //
        //


    }

    public static void hello(String... names) {

    }

}

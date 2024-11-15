package com.lqd.androidpractice.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.lqd.androidpractice.R;

/**
 * 展示基本的UI
 */
public class UIExampleActivity extends AppCompatActivity {

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //        overridePendingTransition(com.swmansion.rnscreens.R.anim.rns_fade_in, com.swmansion.rnscreens.R.anim.rns_fade_out);

        setContentView(R.layout.activity_uiexample);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("UI测试");
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.inflateMenu(R.menu.game_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getTitle().toString()) {
                case "添加":
                    Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();
                    return true;
                case "设置":
                    Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                    return true;

            }
            return false;
        });
        //        setSupportActionBar(toolbar);

        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        for (int i = 0; i < 5; i++) {
            Chip chip = new Chip(this);
            chip.setText("Chip " + i);
            //            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.colorPrimary));
            chipGroup.addView(chip);
        }

        // 悬浮按钮
        findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            Snackbar.make(v, "Here is a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        });

        findViewById(R.id.btn1).setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("test").setPositiveButton("sure", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(UIExampleActivity.this, "You press OK button", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(UIExampleActivity.this, "You press 取消 button", Toast.LENGTH_SHORT).show();
                }
            });

            builder.create().show();
        });

        ImageView imageView = findViewById(R.id.imageView);

        //        String url = getApplicationContext().getCacheDir().getAbsolutePath() + "/ffe4aafab4cec68a55a890d8019feab7897fbd6d98c7c86df7d9f181f0f7e5c6.mp4";
//        String url = getApplicationContext().getCacheDir().getAbsolutePath() + "/qqqqqwwww.mp4";
        String url = getApplicationContext().getCacheDir().getAbsolutePath() + "/飞书大大大.mp4";

        imageView.getWidth();
        int targetWidth = imageView.getResources().getDisplayMetrics().widthPixels;
        int targetHeight = imageView.getResources().getDisplayMetrics().heightPixels;
        Log.w(">>>>>>>>", "######targetWidth: " + targetWidth + " targetHeight: " + targetHeight);

        //        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        //        retriever.setDataSource(url);
        //        Bitmap bitmap = retriever.getFrameAtTime(-1, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
        //        Bitmap bitmap2 = retriever.getFrameAtTime(-1, MediaMetadataRetriever.OPTION_NEXT_SYNC);
        //        Bitmap bitmap3 = retriever.getFrameAtTime(-1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        //        Bitmap bitmap4 = retriever.getFrameAtTime(-1, MediaMetadataRetriever.OPTION_CLOSEST);
        //        Log.w(">>>>>>>>", "######width: "+bitmap.getWidth());
        //        Log.w(">>>>>>>>", "######width2: "+bitmap2.getWidth());
        //        Log.w(">>>>>>>>", "######width3: "+bitmap3.getWidth());
        //        Log.w(">>>>>>>>", "######width4: "+bitmap4.getWidth());

        Glide.with(imageView).setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565)).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                imageView.setImageBitmap(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });


    }


    void addImage() {
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.game_load_progress);
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //
    }


    /// 默认的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getTitle().toString()) {
            case "添加":
                Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();
                return true;
            case "设置":
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}



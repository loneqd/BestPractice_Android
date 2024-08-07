package com.lqd.androidpractice.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

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
    }


    void addImage() {
        ImageView i= new ImageView(this);
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



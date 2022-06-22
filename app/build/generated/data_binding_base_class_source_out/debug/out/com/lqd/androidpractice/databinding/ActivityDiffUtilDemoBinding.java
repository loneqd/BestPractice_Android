// Generated by view binder compiler. Do not edit!
package com.lqd.androidpractice.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.lqd.androidpractice.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDiffUtilDemoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button duBtn;

  @NonNull
  public final RecyclerView duRecyclerView;

  private ActivityDiffUtilDemoBinding(@NonNull ConstraintLayout rootView, @NonNull Button duBtn,
      @NonNull RecyclerView duRecyclerView) {
    this.rootView = rootView;
    this.duBtn = duBtn;
    this.duRecyclerView = duRecyclerView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDiffUtilDemoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDiffUtilDemoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_diff_util_demo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDiffUtilDemoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.du_btn;
      Button duBtn = rootView.findViewById(id);
      if (duBtn == null) {
        break missingId;
      }

      id = R.id.du_recycler_view;
      RecyclerView duRecyclerView = rootView.findViewById(id);
      if (duRecyclerView == null) {
        break missingId;
      }

      return new ActivityDiffUtilDemoBinding((ConstraintLayout) rootView, duBtn, duRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

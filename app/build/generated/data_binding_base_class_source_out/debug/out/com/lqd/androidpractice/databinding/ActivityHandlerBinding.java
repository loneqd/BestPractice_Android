// Generated by view binder compiler. Do not edit!
package com.lqd.androidpractice.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.lqd.androidpractice.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHandlerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button ahBtn1;

  @NonNull
  public final TextView ahTextview;

  private ActivityHandlerBinding(@NonNull LinearLayout rootView, @NonNull Button ahBtn1,
      @NonNull TextView ahTextview) {
    this.rootView = rootView;
    this.ahBtn1 = ahBtn1;
    this.ahTextview = ahTextview;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHandlerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHandlerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_handler, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHandlerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ah_btn1;
      Button ahBtn1 = rootView.findViewById(id);
      if (ahBtn1 == null) {
        break missingId;
      }

      id = R.id.ah_textview;
      TextView ahTextview = rootView.findViewById(id);
      if (ahTextview == null) {
        break missingId;
      }

      return new ActivityHandlerBinding((LinearLayout) rootView, ahBtn1, ahTextview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

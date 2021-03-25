package com.eglisemr.ui.webveiw;

import android.content.Context;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.eglisemr.R;

public class LayerError extends View {

    private View view;

    public LayerError(Context context) {
        super(context);
        FrameLayout layout = findViewById(R.id.layout_error);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.layer_404, null);
        view.setVisibility(GONE);
        layout.addView(view);
    }

    public void set404(){
        this.view.setVisibility(VISIBLE);
    }

}

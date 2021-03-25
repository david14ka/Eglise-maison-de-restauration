package com.eglisemr.ui.home;

import android.content.Intent;
import android.view.View;
import com.eglisemr.ui.webveiw.WebActivity;
import com.eglisemr.model.Resource;

public class ResourceItemOnclick implements View.OnClickListener {
    Resource mResource;
    public ResourceItemOnclick(Resource resource) {
        mResource =resource;
    }

    @Override
    public void onClick(View v) {
        //WebActivity.mResource = mResource;
        Intent intent = new Intent(v.getContext(), WebActivity.class);
        intent.putExtra("title",mResource.getTitle());
        intent.putExtra("url",mResource.getFeedUrl());
        v.getContext().startActivity(intent);
    }
}
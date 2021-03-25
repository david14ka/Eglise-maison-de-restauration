package com.eglisemr.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eglisemr.R;
import com.eglisemr.model.Resource;
import com.squareup.picasso.Picasso;

public class ResourceView extends View implements View.OnClickListener {

    private final TextView title;
    private final TextView subtitle;
    private final ImageView imageView;
    private final View view;

    public ResourceView(@NonNull Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        this.view = inflater.inflate(R.layout.layer_resource, null);
        this.title = this.view.findViewById(R.id.title);
        this.subtitle = this.view.findViewById(R.id.subtitle);
        this.imageView = this.view.findViewById(R.id.image);

    }

    public View withResource(Resource resource){

        setTitle(resource.getTitle());
        setSubtitle(resource.getSubtitle());
        setImage(resource.getImageUrl());

        view.setOnClickListener(new ResourceItemOnclick(resource));

        return this.view;
    }

    private ResourceView setTitle(String title){
        this.title.setText(title);
        return this;
    }

    private ResourceView setSubtitle(String subtitle){
        this.subtitle.setText(subtitle);
        return this;
    }

    private ResourceView setImage(String imageUrl){
        //imageView.setScaleType();
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .into(this.imageView);

        return this;
    }

    @Override
    public void onClick(View v) {

    }
}

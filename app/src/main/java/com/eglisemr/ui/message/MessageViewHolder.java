package com.eglisemr.ui.message;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eglisemr.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView subtitle;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        subtitle = itemView.findViewById(R.id.subtitle);
    }
}

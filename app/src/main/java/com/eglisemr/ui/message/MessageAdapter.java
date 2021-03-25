package com.eglisemr.ui.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eglisemr.R;
import com.eglisemr.model.Sermon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private List<Sermon> sermonList;
    private Context mContext;
    public MessageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.layer_sermon,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Sermon sermon = sermonList.get(position);
        holder.title.setText(sermon.getTitle());
        holder.subtitle.setText(sermon.getSubtitle());

        Picasso.get()
                .load(sermon.getImageUrl())
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                MessageDetailActivity.mSermon = sermon;
                mContext.startActivity(new Intent(mContext,MessageDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return sermonList.size();
    }

    public void setData(List<Sermon> sermons) {
        this.sermonList = sermons;
    }
}

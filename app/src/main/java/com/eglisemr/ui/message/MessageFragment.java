package com.eglisemr.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eglisemr.R;
import com.eglisemr.model.Sermon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageFragment extends Fragment {

    private MessageViewModel messageViewModel;
    private RecyclerView sermonRecyclerView;
    private MessageAdapter messageAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        sermonRecyclerView = root.findViewById(R.id.recycler_view);
        sermonRecyclerView.setHasFixedSize(true);
        sermonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        messageViewModel.getMessages().observe(getViewLifecycleOwner(), new Observer<List<Sermon>>() {
            @Override
            public void onChanged(List<Sermon> sermons) {

                messageAdapter = new MessageAdapter(getContext());
                messageAdapter.setData(sermons);
                sermonRecyclerView.setAdapter(messageAdapter);
            }
        });
        return root;
    }
}
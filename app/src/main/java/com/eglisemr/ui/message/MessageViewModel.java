package com.eglisemr.ui.message;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eglisemr.model.Sermon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.eglisemr.config.MyApplication.DATABASE_URL;

public class MessageViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Sermon>> mSermons;
    private FirebaseDatabase database = FirebaseDatabase.getInstance(DATABASE_URL);
    private DatabaseReference sermonRef = database.getReference("feed/sermons");

    public MessageViewModel() {
        mText = new MutableLiveData<>();
        mSermons = new MutableLiveData<>();
        mText.setValue("Messages");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Sermon>> getMessages() {
        mSermons = new MutableLiveData<>();
        List<Sermon> sermonList = new ArrayList<>();
        sermonRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    for (DataSnapshot snap :
                            dataSnapshot.getChildren()) {
                        sermonList.add(snap.getValue(Sermon.class));
                    }

                    mSermons.setValue(sermonList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return mSermons;
    }
}
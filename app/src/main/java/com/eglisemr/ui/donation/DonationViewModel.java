package com.eglisemr.ui.donation;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eglisemr.config.MyApplication;
import com.eglisemr.model.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.eglisemr.config.MyApplication.DATABASE_URL;

public class DonationViewModel extends ViewModel {

    private String feedUri = "feed/resources/donation";
    private DatabaseReference donationRef;
    private MutableLiveData<Resource> mResourceLiveData;
    private MutableLiveData<String> mText;

    public DonationViewModel() {
        mResourceLiveData = new MutableLiveData<>();
        donationRef = FirebaseDatabase.getInstance(DATABASE_URL)
                .getReference("feed/donation/feedUrl");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Resource> getResource() {
        donationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Resource resource = new Resource();
                    resource.setFeedUrl(dataSnapshot.getValue(String.class));
                    mResourceLiveData.setValue(resource);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mResourceLiveData.setValue(null);
            }
        });

        return mResourceLiveData;
    }
}
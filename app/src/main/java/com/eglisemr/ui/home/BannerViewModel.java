package com.eglisemr.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eglisemr.config.MyApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.eglisemr.config.MyApplication.DATABASE_URL;

public class BannerViewModel extends ViewModel {

    private MutableLiveData<List<String>> mBanners;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance(DATABASE_URL);
    DatabaseReference bannerRef = database.getReference("feed/banners");

    public BannerViewModel() {
        mBanners = new MutableLiveData<>();
    }

    public LiveData<List<String>> getBanners() {

        mBanners = new MutableLiveData<>();

        // Read from the database
        bannerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<String> bannerList = new ArrayList<>();
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    bannerList.add(snapshot.getValue(String.class));
                }

                //mBanners = new MutableLiveData<>();
                mBanners.setValue(bannerList);
                Log.d(TAG, "Value is: " + dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return mBanners;
    }
}
package com.eglisemr.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eglisemr.model.Resource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.eglisemr.config.MyApplication.DATABASE_URL;

public class ResourceViewModel extends ViewModel {

    private MutableLiveData<List<Resource>> mResources;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance(DATABASE_URL);
    DatabaseReference resourceRef = database.getReference("feed/resources");

    public ResourceViewModel() {
        mResources = new MutableLiveData<>();
    }

    public LiveData<List<Resource>> getResources() {

        mResources = new MutableLiveData<>();
        List<Resource> resourceList = new ArrayList<>();
        // Read from the database
        resourceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot :
                            dataSnapshot.getChildren()) {
                        resourceList.add(snapshot.getValue(Resource.class));
                    }

                    mResources.setValue(resourceList);
                    Log.d(TAG, "Value is: " + dataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return mResources;
    }
}
package com.eglisemr.config;

import android.app.Application;
import android.content.ContextWrapper;

import com.eglisemr.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pixplicity.easyprefs.library.Prefs;

public class MyApplication extends Application {

    public static final String DATABASE_URL = "https://eglise-mr-default-rtdb.firebaseio.com/";
    private FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        //DATABASE_URL = getString(R.string.database_url);
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(MyApplication.class.getSimpleName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    public static FirebaseDatabase getDatabaseInstance(){
        MyApplication application = new MyApplication();
        return application.database;
    }

    public static DatabaseReference userRef(){
        return FirebaseDatabase.getInstance(DATABASE_URL).getReference("users");
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }
}

package com.ifsul.remindme;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 20151inf0107 on 14/08/2018.
 */

public class DatabaseUtils {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getFirebaseDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }
}

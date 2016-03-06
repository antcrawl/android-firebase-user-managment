package com.antcrawl;

import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by himanshu_kapoor on 3/5/2016.
 */
public class FirebaseUtill {

    static Firebase firebase;
    public static Firebase getConnection(Context ctx) {

        if(firebase == null) {

            firebase = new Firebase(ctx.getResources().getString(R.string.firebase_url));

            return firebase;
        }
        else
            return firebase;
    }
}

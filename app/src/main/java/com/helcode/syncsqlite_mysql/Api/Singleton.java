package com.helcode.syncsqlite_mysql.Api;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;


public class Singleton {
    private static Singleton mInstance;
    private RequestQueue referenceQueue;
    private static Context mCtx;

    private Singleton(Context context){
        mCtx = context;
        referenceQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(referenceQueue==null){
            referenceQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return referenceQueue;
    }
    public static synchronized Singleton getInstance(Context context){
        if(mInstance==null){
            mInstance = new Singleton(context);
        }
        return  mInstance;
    }
    public<T> void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }
}


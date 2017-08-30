package com.adityadua.dbsqlexample.utils;

import android.content.Context;

import com.adityadua.dbsqlexample.database.DBHelper;

/**
 * Created by AdityaDua on 29/08/17.
 */

public class CommonUtilities {

    public static DBHelper getDBObject(Context mContext){
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        return dbHelper;
    }
}

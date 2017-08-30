package com.adityadua.dbsqlexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.adityadua.dbsqlexample.utils.Constants;

/**
 * Created by AdityaDua on 29/08/17.
 */

public class TablesClass extends SQLiteOpenHelper {

    Context context;
    String cQuery = "Create table IF NOT EXISTS " + Constants.BOOK_RECORD +" ("+
            Constants.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Constants.BOOK_ID+" TEXT,"+
            Constants.BOOK_NAME+" TEXT,"+
            Constants.BOOK_AUTHOR+" TEXT )";

    public TablesClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This method is called whenever you are updating
        // you have downloaded a new version ,...
        // you will delete the old one first...

        context.deleteDatabase(Constants.DATABASE_NAME);
        onCreate(db);

    }
}

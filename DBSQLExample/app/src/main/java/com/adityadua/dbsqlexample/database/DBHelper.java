package com.adityadua.dbsqlexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.adityadua.dbsqlexample.model.BookData;
import com.adityadua.dbsqlexample.utils.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AdityaDua on 29/08/17.
 */

public class DBHelper {

    private SQLiteDatabase db;
    private final Context context;
    private final TablesClass dbHelper;
    private static DBHelper db_helper;

    public DBHelper(Context context) {
        this.context = context;
        // this will create a new DB for you..
        dbHelper = new TablesClass(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

    }

    public static  DBHelper getInstance(Context context){
        try{
            if(db_helper == null){
                db_helper = new DBHelper(context);
                db_helper.open();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  db_helper;
    }

    public void open() throws SQLiteException {
        try{
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
            db= dbHelper.getReadableDatabase();
        }
    }

    public void close(){
        if(db.isOpen()){
            db.close();
        }
    }
    // insert values into the DB
    public long  insertContentVals(String tableName, ContentValues contentValues){
        long id =0;
        try{
            db.beginTransaction();
            id= db.insert(tableName,null,contentValues);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return  id;
    }

    public Cursor getTableRecords(String tableName,String[] columns,String where,String orderby){

        Cursor c = db.query(false,tableName,columns,where,null,null,null,orderby,null);
        return  c;
    }

    //Total number of records present in the table

    /**
     * This method is to get the total number of rows..
     * count of teh rows :: 1 params : table Name & condition
     * run the query ....
     * return the CURSOR Object
     * if c == null => exitt
     * else fetch the number of
     *          1. Go to the first row
     *          2. Count the rows
     *
     * close the cursor
     * return count
     *
     * @param tableName
     * @param where
     * @return
     */
    public int getFullContent(String tableName,String where){
        //clear till here...
        int rowCount = 0;
        Cursor c = db.query(false,tableName,null,where, null,null,null,null,null);
        // Iterate over the cursor
        try {
            c.moveToFirst();
            if (c != null) {
                rowCount = c.getCount();
            }
        }finally {
            c.close();
        }
        return rowCount;

    }
    //delete student where id = 1,2;

    /**
     * Method to delete the rows from table
     * @param table
     * @param where
     * @param whereArgs
     */
    public void deleteRecord(String table, String where,String[] whereArgs){
        try{
            db.beginTransaction();
            db.delete(table,where,whereArgs);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    /**
     * What does update query gives you as an OUTPUT...
     *
     * @param tableName
     * @param values
     * @param where
     * @param whereArgs
     */
    public int updateRecord(String tableName,ContentValues values,String where,String[] whereArgs){
        int rowCount =0 ;

        try{
            db.beginTransaction();
            rowCount = db.update(tableName,values,where,whereArgs);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return rowCount;
    }

    // Get a row from a table based on a particular scenereo

    public String getValue(String tableName,String column,String where){

        Cursor resultCursor = db.query(false,tableName, new String[] {column},where,null,null,null,Constants.ID,null);
        String value= "";
        try{
            if(resultCursor.moveToFirst()){
                value = resultCursor.getString(0);

            }else{
                return null;
            }
        }finally {
            resultCursor.close();
        }

        return  value;
    }


    public List<BookData> getAllBooks(){
        List<BookData> books = new LinkedList<>();

        String query = "select * from "+Constants.BOOK_RECORD;

        Cursor c= db.rawQuery(query,null);
        BookData book = null;

        if(c.moveToFirst()){
            do{
                book = new BookData();
                book.setId(c.getString(0).toString());
                book.setBookId(c.getString(1));
                book.setBookName(c.getString(2));
                book.setBookAuthor(c.getString(3));

                books.add(book);
            }while (c.moveToNext());
        }
        c.close();
        return books;

    }



}

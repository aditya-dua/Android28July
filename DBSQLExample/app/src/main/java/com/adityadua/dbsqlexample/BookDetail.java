package com.adityadua.dbsqlexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adityadua.dbsqlexample.database.DBHelper;
import com.adityadua.dbsqlexample.utils.CommonUtilities;
import com.adityadua.dbsqlexample.utils.Constants;

/**
 * Created by AdityaDua on 30/08/17.
 */

public class BookDetail extends AppCompatActivity{

    TextView bookTitle,authorTitle;
    EditText title_edt,author_edt;
    Button deleteBtn;
    DBHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_detail);
        bookTitle = (TextView)findViewById(R.id.title1);
        authorTitle = (TextView)findViewById(R.id.author);

        title_edt= (EditText)findViewById(R.id.titleEdit);
        author_edt = (EditText)findViewById(R.id.authorEdit);

        Intent i = getIntent();
        String id = i.getStringExtra(Constants.BOOK_ID);

        db= CommonUtilities.getDBObject(this);

        String bookName = db.getValue(Constants.BOOK_RECORD,Constants.BOOK_NAME,Constants.BOOK_ID +" = '"+id+"';");

        String authorName = db.getValue(Constants.BOOK_RECORD,Constants.BOOK_AUTHOR,Constants.BOOK_ID +" = '"+id+"';");

        bookTitle.setText(bookName);
        authorTitle.setText(authorName);

        deleteBtn = (Button)findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRecord(Constants.BOOK_RECORD,Constants.BOOK_ID +" = '"+getIntent().getStringExtra(Constants.BOOK_ID)+"'",null);
            }
        });
    }
}

package com.adityadua.dbsqlexample;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adityadua.dbsqlexample.database.DBHelper;
import com.adityadua.dbsqlexample.model.BookData;
import com.adityadua.dbsqlexample.utils.CommonUtilities;
import com.adityadua.dbsqlexample.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
// First give me a demo of insert

    String[] book_names=new String[]{
            "My Experiments with Truth",
            "The Pursuit Of Happyness",
            "Geetanjali",
            "The Monk who sold his Ferrari"
    };

    String[] authors_names=new String[]{
            "M.K.Gnadhi",
            "Chris Gardner",
            "R N Tagore",
            "Robin Sharma"
    };

    String[] ids=new String[]{
            "1234",
            "2345",
            "3456",
            "4567"
    };
    ListView list;
    ArrayAdapter<String> myAdaptor;
    DBHelper dbHelper;
    List<BookData> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = CommonUtilities.getDBObject(this);
        list = (ListView)findViewById(R.id.list);


        int count = dbHelper.getFullContent(Constants.BOOK_RECORD,null);
        if(count ==0){
            insertBook();
        }

        dataList = dbHelper.getAllBooks();
        List<String> listTitle= new ArrayList<String>();

        for(int i =0;i<dataList.size();i++){
            listTitle.add(dataList.get(i).getBookName());
        }

        myAdaptor = new ArrayAdapter<String>(this,R.layout.row_layout,R.id.listText,listTitle);

        myAdaptor.notifyDataSetChanged();

        list.setAdapter(myAdaptor);
        list.setOnItemClickListener(this);

        // insert the values into the table

    }

    private void insertBook(){
        for(int i=0;i<book_names.length;i++){
            ContentValues val = new ContentValues();
            val.put(Constants.BOOK_ID,ids[i]);
            val.put(Constants.BOOK_NAME,book_names[i]);
            val.put(Constants.BOOK_AUTHOR,authors_names[i]);
            dbHelper.insertContentVals(Constants.BOOK_RECORD,val);



        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent(this,BookDetail.class);
        i.putExtra(Constants.BOOK_ID,ids[position]);
        startActivityForResult(i,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dataList = dbHelper.getAllBooks();
        List<String> listTitle= new ArrayList<String>();

        for(int i =0;i<=dataList.size();i++){
            listTitle.add(i,dataList.get(i).getBookName());
        }

        myAdaptor = new ArrayAdapter<String>(this,R.layout.row_layout,R.id.listText,listTitle);

        list.setAdapter(myAdaptor);
        list.setOnItemClickListener(this);

    }
}

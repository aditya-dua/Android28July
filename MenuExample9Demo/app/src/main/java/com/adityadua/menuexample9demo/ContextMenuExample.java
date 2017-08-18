package com.adityadua.menuexample9demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by AdityaDua on 16/08/17.
 */

public class ContextMenuExample extends Activity {

    // 1. Create the List View
    ArrayList<String> contacts;
    ListView list;
    //String [] exmp= {"Contact1","Contact2","Contact3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.context_exmaple);
        contacts = new ArrayList<String>();

        for (int i = 0 ;i<10;i++){
            contacts.add("Contact "+i);
        }

        list= (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,contacts);
        list.setAdapter(adapter);

        TextView tv = (TextView)findViewById(R.id.textView3);
        // in andorid every UI component is a View : TextView | EditText | Button
        registerForContextMenu(tv);

        registerForContextMenu(list);

    }


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void initList(){
        contacts = new ArrayList<>();

        for (int i = 0 ;i<10;i++){
            contacts.add("Contact "+(i+1));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("List Options");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        // group
        // item Id
        // order

        //add(group id,menu item id,order,title)
        if(v.getId() == R.id.list) {
            menu.add(1,1,3,"Cut");
            menu.add(1,2,2,"Copy");
            menu.add(1,3,1,"Paste");
        }
        else{
            menu.add(10,1,0,"Delete");
        }

        // for Copy : group : 1

       /* if(v.){
        menu.add(1,10,1,"Copy");}*/


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {



        if(item.getGroupId()== 10 ) {
            Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
        }else if(item.getGroupId()==1){
            Toast.makeText(this, "List Context Clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}

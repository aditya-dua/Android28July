package com.adityadua.advanceduicomponents7demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by AdityaDua on 14/08/17.
 */

public class ListExample extends Activity {
    // Coding for List View
    // 1. Create an Array
    String[] subjects = {
            "Android Application Development",
            "Core Java",
            "PHP",
            "Big Data & Analytics",
            "Advanced Java",
            "Asp.NET",
            "Spring Frameworks",
            "Hibernate"
    };
    //2. Create the object of List View
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_exmaple);

        list = (ListView)findViewById(R.id.list);

        //3. Initilize your ArrayAdaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjects);

        //4. Link your adaptor to List View
        list.setAdapter(adapter);
//5. Setting OnclickListener on the List View
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ListExample.this, "Item Clicked Is : "+subjects[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}

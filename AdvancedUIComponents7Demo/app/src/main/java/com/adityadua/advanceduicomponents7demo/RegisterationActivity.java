package com.adityadua.advanceduicomponents7demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by AdityaDua on 14/08/17.
 */

public class RegisterationActivity extends Activity {

    EditText name,phone;
    RadioGroup gender;
    CheckBox eng_check,fluency_check,ielts_check,toefl_check;
    Button submit;
    Spinner age_group;
    String [] age={
            "10-18",
            "19-22",
            "23-28",
            "29-35",
            "35 & above"
    };
    String age_selected="";
    String gender_selected="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_ui);

        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);

        gender = (RadioGroup) findViewById(R.id.gender_group);

        eng_check = (CheckBox)findViewById(R.id.english_chk);
        fluency_check = (CheckBox)findViewById(R.id.fluency_chk);
        ielts_check = (CheckBox)findViewById(R.id.ielts_chk);
        toefl_check = (CheckBox)findViewById(R.id.toefl_chk);

        age_group = (Spinner)findViewById(R.id.age_grup);

        submit = (Button)findViewById(R.id.submit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,age);

        age_group.setAdapter(adapter);
        age_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age_selected = age[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton malebtn = (RadioButton)findViewById(R.id.male);
                if(malebtn.isChecked()){
                    gender_selected=malebtn.getText().toString();
                }
                /*if(checkedId == R.id.male){
                    gender_selected = "Male";
                }else if(checkedId ==R.id.female){
                    gender_selected = "Female";
                }*/
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = "Name :"+name.getText().toString()+
                            "Phone :"+phone.getText().toString()+
                        "Gender :"+gender_selected+
                        "Age Group :"+age_selected+
                        "English :"+eng_check.isChecked()+
                        "Fluency :"+fluency_check.isChecked()+
                        "IELTS :"+ielts_check.isChecked()+
                        "TOEFL :"+toefl_check.isChecked();

                Toast.makeText(RegisterationActivity.this, Message, Toast.LENGTH_SHORT).show();
            }

        });

    }
}

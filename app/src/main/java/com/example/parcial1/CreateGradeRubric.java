package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.parcial1.Models.Activity;

import java.util.ArrayList;

public class CreateGradeRubric extends AppCompatActivity {

    EditText txtActivity,txtPercentage, txtGrade;
    Button btnAddItem, btnSave;
    ArrayList<Activity> activities;
    ListView listView;
    ArrayAdapter<Activity> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grade_rubric);
        txtActivity=findViewById(R.id.txtActivity);
        txtPercentage=findViewById(R.id.txtPercentage);
        txtActivity=findViewById(R.id.txtGrade);
        btnAddItem=findViewById(R.id.btnAddItem);
        btnSave=findViewById(R.id.btnSave);
        activities=new ArrayList<>();
        adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,activities);

        listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity=new Activity();
                activity.setName(txtActivity.getText().toString());
                activity.setGrade(Float.parseFloat(txtGrade.getText().toString()));
                activity.setPercentage(Float.parseFloat(txtPercentage.getText().toString())/100);
                activities.add(activity);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void DrawList(){

    }
}

package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.parcial1.Repositories.DatabaseHelper;
import com.example.parcial1.Repositories.SubjectRepository;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button btnGrade, btnSubject, btnSubjectList, btnGradesList,btnSubjectManagement;
    SubjectRepository subjectRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        subjectRepository=new SubjectRepository(this);
        btnSubject= (Button)findViewById(R.id.btnSubject);
        btnGrade=(Button)findViewById(R.id.btnGrade);
        btnSubjectList=(Button)findViewById(R.id.btnSubjectList);
        btnGradesList= findViewById(R.id.btnGradesList);
        btnSubjectManagement=findViewById(R.id.btnSubjectManagement);
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubjectAdd.class));
            }
        });
        btnGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubjectSearch.class));
            }
        });
        btnSubjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSubjects();
            }
        });
        btnGradesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listGrades();
            }
        });
        btnSubjectManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubjectManagement.class));
            }
        });
    }
    private void listSubjects(){
        Cursor res =subjectRepository.getAllSubjects();
        if(res.getCount()<=0){
            showToast("No hay materias");
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
           buffer.append("ID: "+res.getString(0)+"\n");
           buffer.append("Nombre: "+res.getString(1)+"\n");
           buffer.append("\n");

        }
        showMessage("Materias",buffer.toString());
    }
    private void listGrades(){
        Cursor res =subjectRepository.getAllGrades();
        if(res.getCount()<=0){
            showToast("No hay Calificaciones");
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID: "+res.getString(1)+"\n");
            buffer.append("Materia: "+res.getString(2)+"\n");
            float n1=Float.parseFloat(res.getString(3));
            float n2=Float.parseFloat(res.getString(4));
            float n3=Float.parseFloat(res.getString(5));
            buffer.append("Nota 1: "+n1+"\n");
            buffer.append("Nota 2: "+n2+"\n");
            buffer.append("Nota 3: "+n3+"\n");
            buffer.append("Def: "+calculateDef(n1,n2,n3)+"\n");
            buffer.append("\n");
        }
        showMessage("Materias",buffer.toString());
    }

    public float calculateDef(float n1,float n2,float n3){
        float def=((n1*0.3f)+(n2*0.3f)+(n3*0.4f));
        return def;
    }
    private void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial1.Repositories.SubjectRepository;

public class ReportCard extends AppCompatActivity {
    Button btnSave;
    TextView lblIdSubject,lblSubjectName,lblDef;
    SubjectRepository subjectRepository;
    EditText txtN1,txtN2,txtN3;
    int idC1,idC2,idC3;
    float n1=0,n2=0,n3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_card);
        btnSave=findViewById(R.id.btnSave);
        txtN1=findViewById(R.id.txtN1);
        txtN2=findViewById(R.id.txtN2);
        txtN3=findViewById(R.id.txtN3);
        final String[] subject =getIntent().getStringArrayExtra("subject");
        lblIdSubject=findViewById(R.id.lblIdSubject);
        lblSubjectName=findViewById(R.id.lblSubjectName);
        lblDef=findViewById(R.id.lblDef);
        lblSubjectName.setText(subject[1]);
        lblIdSubject.setText(subject[0]);
        btnSave.setEnabled(true);
        subjectRepository= new SubjectRepository(this);
        txtN3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                    calculateDef();
            }
        });
        txtN1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)calculateDef();
            }
        });
        txtN2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)calculateDef();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               calculateDef();
               if(n1>5 || n1<0||n2>5 || n2<0||n3>5 || n3<0){
                   showMessage("Información","No se guardó. Valores fueras de rango");
               }else{
                boolean inserted=subjectRepository.insertGrades(subject[0],subject[1],n1,n2,n3);
                if(inserted){
                    showMessage("Información","Se guardó correctamente");
                }
                btnSave.setEnabled(false);
               }
            }
        });
    }

    public void calculateDef(){
        try {
            n1=Float.parseFloat(txtN1.getText().toString());
        }catch (Exception e){
            n1=0;
        }
        try {
            n2=Float.parseFloat(txtN2.getText().toString());
        }catch (Exception e){
            n2=0;
        }
        try {
            n3=Float.parseFloat(txtN3.getText().toString());
        }catch (Exception e){
            n3=0;
        }
        float def=((n1*0.3f)+(n2*0.3f)+(n3*0.4f));
        lblDef.setText(String.valueOf(def));
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

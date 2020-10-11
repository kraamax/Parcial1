package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial1.Repositories.SubjectRepository;

public class SubjectManagement extends AppCompatActivity {
    EditText txtIdSubject,txtSubjectName;
    Button btnUpdate,btnDelete,btnSearch;
    SubjectRepository subjectRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_management);
        txtIdSubject=findViewById(R.id.txtIdSubject);
        txtSubjectName=findViewById(R.id.txtSubjectName);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnSearch=findViewById(R.id.btnSearch);
        subjectRepository=new SubjectRepository(this);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=subjectRepository.updateData(txtIdSubject.getText().toString(),txtSubjectName.getText().toString());
                if(res){
                    showMessage("Información","Se ha actualizado");
                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtIdSubject.getText().toString().equals(""))search();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleted=subjectRepository.deleteData(txtIdSubject.getText().toString());
                if(deleted)
                {
                    txtSubjectName.setText("");
                    txtIdSubject.setText("");
                    showMessage("Informacion","La materia se ha eliminado");
                }else{
                    showToast("No se encontró");
                }
            }
        });
    }
    public void search() {
            Cursor res=subjectRepository.getSubjectById(txtIdSubject.getText().toString());
            if(res.getCount()<=0){
                showMessage("Información","Materia no encontrada");
                return;
            }else
            {
                String[] subject=new String[2];
                while(res.moveToNext()){
                    subject[0]=res.getString(0);
                    subject[1]=res.getString(1);
                }
                txtSubjectName.setText(subject[1]);
            }

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

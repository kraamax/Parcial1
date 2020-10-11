package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial1.Repositories.DatabaseHelper;
import com.example.parcial1.Repositories.SubjectRepository;

public class SubjectAdd extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText idSubject,subjectName;
    Button btnSave;
    SubjectRepository subjectRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add);
        databaseHelper = new DatabaseHelper(this);
        subjectRepository= new SubjectRepository(this);
        idSubject=(EditText)findViewById(R.id.txtIdSubject);
        subjectName=(EditText)findViewById(R.id.txtSubjectName);
        btnSave=(Button)findViewById(R.id.btnSave);
        saveSubject();
    }
    public void saveSubject(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted=subjectRepository.insertData(idSubject.getText().toString(),subjectName.getText().toString());
                if(inserted){
                    showToast("Se ha agregó correctamente");
                    idSubject.setText("");
                    subjectName.setText("");
                }else{
                    showToast("No se agregó");
                }
            }
        });
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

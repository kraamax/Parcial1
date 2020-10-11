package com.example.parcial1.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SubjectRepository extends SQLiteOpenHelper {

    public SubjectRepository(Context context) {
        super(context,"MARKS.db",null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public boolean insertData(String idSubject,String subjectName) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ID_SUBJECT",idSubject);
        contentValues.put("SUBJECT_NAME",subjectName);
        long result=db.insert("SUBJECTS",null, contentValues);
        return result!=-1;
    }
    public boolean insertGrades(String idSubject,String subjectName,float n1, float n2,float n3) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("FK_ID_SUBJECT",idSubject);
        contentValues.put("SUBJECT_NAME",subjectName);
        contentValues.put("N1",n1);
        contentValues.put("N2",n2);
        contentValues.put("N3",n3);
        long result=db.insert("GRADES",null, contentValues);
        return result!=-1;
    }
    public Cursor getAllSubjects(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from  subjects", null);
        return res;
    }
    public Cursor getSubjectById(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + "SUBJECTS" + " where " + "ID_SUBJECT" + "='" + id + "'" , null);
        return res;
    }
    public boolean updateData(String idSubject, String subjectName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("SUBJECT_NAME",subjectName);
        int a=db.update("SUBJECTS",contentValues,"ID_SUBJECT = ?",new String[]{idSubject});
        return true;
    }
    public  boolean deleteData(String idSubject){
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete("SUBJECTS", "ID_SUBJECT = ?", new String[]{idSubject});
        return res>0;
    }
    public Cursor getCortesBySubject(String idSubject){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from  cortes where FK_ID_SUBJECT='"+idSubject+"'", null);
        return res;
    }
    public Cursor getAllGrades(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from  grades", null);
        return res;
    }
}

package com.example.parcial1.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="MARKS.db";
    public static final String TABLE_NAME="Materias";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table SUBJECTS (ID_SUBJECT TEXT PRIMARY KEY, SUBJECT_NAME TEXT)");
        db.execSQL("create table GRADES (ID_GRADE INTEGER PRIMARY KEY AUTOINCREMENT,FK_ID_SUBJECT TEXT,SUBJECT_NAME, N1 NUMBER,N2 NUMBER,N3 NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists SUBJECTS");
        onCreate(db);
    }
    public boolean insertData(String idSubject,String subjectName) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ID_SUBJECT",idSubject);
        contentValues.put("SUJECT_NAME",subjectName);
        long result=db.insert("SUBJECTS",null, contentValues);
        return result!=-1;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from  subjects", null);
        return res;
    }
    public boolean updateData(String idSubject, String subjectName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("SUJECT_NAME",subjectName);
        int a=db.update("SUBJECTS",contentValues,"ID_SUBJECT = ?",new String[]{idSubject});
        return true;
    }
    public  boolean deleteData(String idSubject){
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete("SUBJECTS", "ID_SUBJECT = ?", new String[]{idSubject});
        return res>0;
    }
}

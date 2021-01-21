package com.example.laboratoriosqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Usuarios.db";
    public static final String TABLE_NAME="Registe_user_table";
    public static final String col_1="ID";
    public static final String col_2="FULLNAME";
    public static final String col_3="USERNAME";
    public static final String col_4="EMAIL";
    public static final String col_5="PASSWORD";
    public static final String col_6="GENDER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FULLNAME TEXT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, GENDER INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }

    public void initData(){
        SQLiteDatabase db= this.getWritableDatabase();
        onUpgrade(db,1,1);
    }
    public boolean insertData(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,user.getFullName());
        contentValues.put(col_3,user.getUserName());
        contentValues.put(col_4,user.geteMail());
        contentValues.put(col_5,user.getPassword());
        contentValues.put(col_6,user.getGender());

        long result =db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }

    public Cursor getData(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where id="+id+"",null);
        return res;
    }

    public Cursor findDataByEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+col_4+"= '"+email+"'",null);
        return res;
    }
}

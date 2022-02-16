package com.f18.mydb3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper
{
    //define all variables so that you can change variable to change all
    private static final int DATABASE_VERSION=1;
    private static final String  DATABASE_NAME="arjad_db";
    private static final String TABLE_NAME="arjad_table";
    private static final String ID="id";
    private static final String name="name";
    private static final String email="email";
    private static final String phone="phones";
    private static final String pass= "pass";
    private static final String created_at="created_at";


    public DBHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY,"+
                name+" TEXT ,"+
                email+" TEXT ,"+
                phone+"TEXT,"+
                pass+"TEXT,"+
                created_at+ " TEXT "+
                ")";
        db.execSQL(table_query); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME); }

public void AddUser(UserModel um){
SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(name,um.getName());
    cv.put(email,um.getEmail());
    cv.put(phone,um.getPhone());
    cv.put(pass, um.getPass());
    cv.put(created_at,um.getCreated_at());
    db.insert(TABLE_NAME,null,cv);
    db.close();
}

public List<UserModel> getALLUsers(){
        List<UserModel> userModelList = new ArrayList<>();
        String query = "SELECT * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                UserModel userModel = new UserModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                userModelList.add(userModel);
            }while(cursor.moveToNext());
        }

        db.close();
        return userModelList;

    }

    public int getTotalCount(){
        String query = "SELECT * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
    return cursor.getCount();
    }
}

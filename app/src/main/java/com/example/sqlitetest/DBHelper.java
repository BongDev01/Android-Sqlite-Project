package com.example.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "test.db";
    public static String TABLE_NAME = "userTable";
    public static int DB_VERSION = 1;
    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";
    private static final String AGE = "Age";

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            "(" +NAME+" VARCHAR(255) , "+AGE+" INTEGER, "+ADDRESS+" TEXT" + ");";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    public long addValueDataBase(Model model){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME,model.getName());
        values.put(AGE,model.getAge());
        values.put(ADDRESS,model.getAddress());
      long rowId =  database.insert(TABLE_NAME,null,values);
      return rowId;
    }

    public List<Model> showAllData(){
        List<Model> modelList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Model model = new Model(cursor.getString(0),""+cursor.getInt(1),cursor.getString(2));
                modelList.add(model);
            }
            while (cursor.moveToNext());
        }

          return modelList;
    }

    public Model showSpecificRow(String name){

        Model model = null;
        SQLiteDatabase database = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE "+NAME+"="+"'"+name+"'";
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            model = new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        }

      return model;
    }

    public int updateValue(Model model){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, model.Name);
        contentValues.put(AGE, model.Age);
        contentValues.put(ADDRESS, model.Address);



       int res =  db.update(TABLE_NAME,contentValues,"Name = ? ",new String[]{model.Name});
      return res;
    }

    public int deleteValue(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, model.Name);
        contentValues.put(AGE, model.Age);
        contentValues.put(ADDRESS, model.Address);

        int ss = db.delete(TABLE_NAME,"Name = ? ",new String[]{model.Name});
        return ss;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

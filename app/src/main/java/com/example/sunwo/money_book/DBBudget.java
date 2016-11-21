package com.example.sunwo.money_book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBBudget extends SQLiteOpenHelper {

    public DBBudget(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // cr)eate table 테이블명 (컬럼명 타입 옵션;
        db.execSQL("CREATE TABLE MONEY_BUD( _id INTEGER PRIMARY KEY AUTOINCREMENT, budget INTEGER, period INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createTable(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void drop(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintDataBudget() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from MONEY_BUD", null);
        cursor.moveToNext();
        cursor.moveToNext();
        str =  " Total Budget = "+ cursor.getInt(1) + "\n";
        return str;
    }
    public String PrintDataRecommend() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from MONEY_BUD", null);
        cursor.moveToNext();
        cursor.moveToNext();
            str =  " Recommended usage = "
                    + cursor.getInt(1)/cursor.getInt(2) + "\n";
        return str;
    }

    public int getBudget() {
        SQLiteDatabase db = getReadableDatabase();
        int budget = 0;

        Cursor cursor = db.rawQuery("select * from MONEY_BUD", null);
        cursor.moveToNext();
        cursor.moveToNext();
        budget = cursor.getInt(1);
        return budget;
    }

    public int getBid() {
        SQLiteDatabase db = getReadableDatabase();
        int bid = 0;

        Cursor countT = db.rawQuery("select * from MONEY_BUD", null);
        while(countT.moveToNext()){
            bid = countT.getInt(0);
        }
        if(bid == 1){
            return -1;
        }
        else {
      /*      Cursor cursor = db.rawQuery("select * from MONEY_BUD", null);
            cursor.moveToNext();
            budget = cursor.getInt(0);
            return budget; */
            return 2;
        }
    }

    public String PrintDataTesting() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from MONEY_BUD", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : bid "
                    + cursor.getInt(1)
                    + ", budget = "
                    + cursor.getInt(2)
                    + ", period = "
                    + "\n";
        }
        return str;
    }
}
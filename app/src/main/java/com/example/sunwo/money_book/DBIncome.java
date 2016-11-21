package com.example.sunwo.money_book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBIncome extends SQLiteOpenHelper {

    public IncomeStruct[] incomeStructs = new IncomeStruct[100];

    public DBIncome(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // cr)eate table 테이블명 (컬럼명 타입 옵션;
        db.execSQL("CREATE TABLE MONEY_IN( _id INTEGER PRIMARY KEY AUTOINCREMENT, income INTEGER, category TEXT, date INTEGER," +
                "incomeMethod TEXT, description TEXT);");
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

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from MONEY_IN", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : income "
                    + cursor.getInt(1)
                    + ", category = "
                    + cursor.getString(2)
                    + ", Date = "
                    + cursor.getInt(3)
                    + ", "
                    + cursor.getString(4)
                    +", "
                    + cursor.getString(5)
                    + "\n";
        }

        return str;
    }

    public int searchIncome(int startDate, int endDate){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from MONEY_IN where date >= "+startDate+" and date <="+ endDate+"", null);
        int count = 0;
        for(int i=0; i<incomeStructs.length; i++){
            incomeStructs[i] = new IncomeStruct();
        }
        while(cursor.moveToNext()){
            String tempAmount = String.valueOf(cursor.getInt(1));
            String tempDate = String.valueOf(cursor.getInt(3));
            incomeStructs[count].setAmount(tempAmount);
            incomeStructs[count].setCategory(cursor.getString(2));
            incomeStructs[count].setDate(tempDate);
            incomeStructs[count].setMethod(cursor.getString(4));
            incomeStructs[count].setDescription(cursor.getString(5));
            count++;
        }
        return count;
    }
}

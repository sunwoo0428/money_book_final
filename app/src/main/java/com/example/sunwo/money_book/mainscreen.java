package com.example.sunwo.money_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class mainscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        final DBExpense dbExpense = new DBExpense(getApplicationContext(), "money_ex.db", null, 1);


    }
}

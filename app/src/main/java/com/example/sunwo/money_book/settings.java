package com.example.sunwo.money_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class settings extends AppCompatActivity {

    String datePractice = "";
    String datePractice2= "";
    String str = "";
    int tempyear = 0;
    int tempmonth = 0;
    int tempday = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final DBExpense dbExpense = new DBExpense(getApplicationContext(), "money_ex.db", null, 1);
        final DBIncome dbIncome = new DBIncome(getApplicationContext(), "money_in.db", null, 1);
        final DBBudget dbBudget = new DBBudget(getApplicationContext(), "money_bdg.db", null, 1);

        TextView testDate = (TextView)findViewById(R.id.datetest);

 /*       SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd",
                Locale.KOREA);
        Date currentTime = new Date();
        datePractice = mSimpleDateFormat.format(currentTime);
        tempyear = Integer.parseInt(datePractice.substring(0,4));
        tempmonth = Integer.parseInt(datePractice.substring(4,6));
        tempday = Integer.parseInt(datePractice.substring(6,8));
        dbExpense.get7DaysAgoGraph(tempyear, tempmonth, tempday);
        for(int i = 1; i<8;i++){
            str += dbExpense.days7graph[i] + "\n";
        }
        testDate.setText(str); */

        Button btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dbExpense.drop("DROP TABLE IF EXISTS MONEY_EX");
                dbExpense.createTable("CREATE TABLE MONEY_EX( _id INTEGER PRIMARY KEY AUTOINCREMENT, expense INTEGER, category TEXT, " +
                        "date INTEGER, paymentMethod TEXT, description TEXT);");
                dbIncome.drop("DROP TABLE IF EXISTS MONEY_IN");
                dbIncome.createTable("CREATE TABLE MONEY_IN( _id INTEGER PRIMARY KEY AUTOINCREMENT, income INTEGER, category TEXT, date INTEGER," +
                        "incomeMethod TEXT, description TEXT);");

                dbBudget.drop("DROP TABLE IF EXISTS MONEY_BUD");
                dbBudget.createTable("CREATE TABLE MONEY_BUD( _id INTEGER PRIMARY KEY AUTOINCREMENT, budget INTEGER, period INTEGER);");
                dbBudget.insert("insert into MONEY_BUD values(null, "+50000+", "+5+");");

                Toast toast = Toast.makeText(getApplicationContext(), "초기화 되었습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}

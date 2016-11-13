package com.example.sunwo.money_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class budget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        final DBExpense dbExpense = new DBExpense(getApplicationContext(), "money_ex.db", null, 1);
        final DBBudget dbBudget = new DBBudget(getApplicationContext(), "money_bdg.db", null, 1);

        final TextView bdgResult = (TextView) findViewById(R.id.total_bdg);
        final TextView recResult = (TextView) findViewById(R.id.recommend_mny);

        final EditText etBudget = (EditText) findViewById(R.id.et_budget);
        final EditText etPeriod = (EditText) findViewById(R.id.et_period);

        Button btnInsertBdg = (Button) findViewById(R.id.btn_insertBdg);
        btnInsertBdg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String budget = etBudget.getText().toString();
                String period = etPeriod.getText().toString();

               if(budget.equals("")||period.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "예산/기간을 모두 입력하시오", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    dbBudget.insert("insert into MONEY_BUD values(null, "+budget+", "+period+");");
                    bdgResult.setText( dbBudget.PrintDataBudget() );
                    recResult.setText( dbBudget.PrintDataRecommend() );
                }

            }
        });

        Button btnDropBdg = (Button) findViewById(R.id.btn_dropBdg);
        btnDropBdg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbBudget.drop("DROP TABLE IF EXISTS MONEY_BUD");
                dbBudget.createTable("CREATE TABLE MONEY_BUD( _id INTEGER PRIMARY KEY AUTOINCREMENT, budget INTEGER, period INTEGER);");
                bdgResult.setText("");
                recResult.setText("");
            }
        });

        Button btnUpdateBdg = (Button) findViewById(R.id.btn_updateBdg);
        btnUpdateBdg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
 /*               int totalExpense = dbExpense.GetTotalExpense();
                int currentBudget = dbBudget.getBudget();
                int updatedBudget = currentBudget - totalExpense;

                dbBudget.update("update MONEY_BUD set budget = " + updatedBudget + " where _id = " + 1 + ";"); */

                bdgResult.setText( dbBudget.PrintDataBudget() );
                recResult.setText( dbBudget.PrintDataRecommend() );
            }
        });
    }
}

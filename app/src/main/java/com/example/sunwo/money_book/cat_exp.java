package com.example.sunwo.money_book;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class cat_exp extends AppCompatActivity {

    final int DIALOG_DATE = 1;
    EditText Des;
    String amount="";
    String exCategory="미등록";
    String exMethod = "미등록";
    String exDescription = "미등록";
    String dateString = "";
    int dateInt = 20000000;
    String tempMonth = "";
    String tempDate="";
    int exYear = 0;
    int exMonth = 0;
    int exDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_exp);

        final DBBudget dbBudget = new DBBudget(getApplicationContext(), "money_bdg.db", null, 1);
        final DBExpense dbExpense = new DBExpense(getApplicationContext(),"money_ex.db",null,1);

        Des = (EditText) findViewById(R.id.et_description);

        Intent intent = getIntent();
        amount = (String)intent.getSerializableExtra("amount");

        final TextView exResult = (TextView) findViewById(R.id.testingresult);
        exResult.setText(amount); // data 넘어가는지 연습

        Button exDate = (Button) findViewById(R.id.btn_exDate);
        exDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        Button btnEXCategory = (Button)findViewById(R.id.btn_exCategory);
        btnEXCategory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DialogSelectOptionEx();
            }
        });

        Button btnExMethod= (Button)findViewById(R.id.btn_exMethod);
        btnExMethod.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DialogSelectOptionPayEx();
            }
        });

        Button btnExDescription= (Button)findViewById(R.id.btn_exDescription);
        btnExDescription.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                exDescription = Des.getText().toString();
            }
        });

        Button btnInsertEx = (Button) findViewById(R.id.btn_exInsert);
        btnInsertEx.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                    if(dbBudget.getBid()==2){
                        dbExpense.insert("insert into MONEY_EX values(null, " + amount + ", '" + exCategory + "',"
                                + dateInt + ", '"+exMethod+"' , '"+exDescription+"');");
                        exResult.setText(dbExpense.PrintData());
                        int currentBudget = dbBudget.getBudget();
                        int updatedBudget = currentBudget - Integer.parseInt(amount);
                        dbBudget.update("update MONEY_BUD set budget = " + updatedBudget + " where _id = " + 2 + ";");
                    }
                    else if(dbBudget.getBid()==-1){ //예산 없을때 구현 어떻게 하지?ㄷㄷㄷ
                        dbExpense.insert("insert into MONEY_EX values(null, " + amount + ", '" + exCategory + "',"
                                +dateInt + ", '"+exMethod+"' , '"+exDescription+"');");
                        exResult.setText(dbExpense.PrintData());
                        Toast toast = Toast.makeText(getApplicationContext(), "등록된 예산이 없습니다", Toast.LENGTH_SHORT);
                        toast.show();
                    }
            }
        });

        Button btnExClose = (Button)findViewById(R.id.btn_exReturn);
        btnExClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void DialogSelectOptionEx() {
        final String items[] = { "미등록", "음식", "의류", "교통비" };
        AlertDialog.Builder ab = new AlertDialog.Builder(cat_exp.this);
        ab.setTitle("카테고리");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        exCategory = items[whichButton];
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        ab.show();
    }

    private void DialogSelectOptionPayEx() {
        final String items[] = { "미등록", "카드", "현금", "통장" };
        AlertDialog.Builder ab = new AlertDialog.Builder(cat_exp.this);
        ab.setTitle("카테고리");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        exMethod = items[whichButton];
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        ab.show();
    }

    protected Dialog onCreateDialog(int id){
        DatePickerDialog dpd = new DatePickerDialog(cat_exp.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(),year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+ "일", Toast.LENGTH_SHORT).show();
//                exYear = year;
                exMonth = monthOfYear+1;
                exDay = dayOfMonth;
                if(monthOfYear<9)
                    tempMonth = "0"+exMonth;
                else
                    tempMonth = ""+exMonth;

                if(exDay<10)
                    tempDate = "0"+exDay;
                else
                    tempDate = ""+exDay;

                dateString = ""+year+""+tempMonth+tempDate;

                dateInt = Integer.parseInt(dateString);
            }
        },2016,11,11);
        return dpd;
    }
}

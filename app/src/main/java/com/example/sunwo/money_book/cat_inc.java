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

public class cat_inc extends AppCompatActivity {

    EditText Des;
    final int DIALOG_DATE = 1;
    String inCategory="미등록";
    String amount = "";
    String inMethod = "미등록";
    String inDescription = "미등록";
    String tempMonth ="";
    String tempDate = "";
    String dateString ="";
    int dateInt = 20000000;
    int inYear = 0;
    int inMonth = 0;
    int inDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_inc);

        final DBIncome dbIncome = new DBIncome(getApplicationContext(),"money_in.db",null,1);

        Des = (EditText) findViewById(R.id.et_description);

        Intent intent = getIntent();
        amount = (String)intent.getSerializableExtra("amount");

        final TextView inResult = (TextView) findViewById(R.id.testingresult);
        inResult.setText(amount); // data 넘어가는지 연습

        Button inDate = (Button) findViewById(R.id.btn_inDate);
        inDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

        Button btnINCategory = (Button)findViewById(R.id.btn_inCategory);
        btnINCategory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DialogSelectOptionIn();
            }
        });

        Button btnInMethod= (Button)findViewById(R.id.btn_inMethod);
        btnInMethod.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                DialogSelectOptionPayIn();
            }
        });

        Button btnInDescription= (Button)findViewById(R.id.btn_inDescription);
        btnInDescription.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                inDescription = Des.getText().toString();
            }
        });


        Button btnInsertIn = (Button) findViewById(R.id.btn_inInsert);
        btnInsertIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    dbIncome.insert("insert into MONEY_IN values(null, " + amount + ", '" + inCategory + "',"
                            + dateInt + ", '"+inMethod+"' , '"+inDescription+"');");
                    inResult.setText(dbIncome.PrintData());
                }
        });

        Button btnInClose = (Button)findViewById(R.id.btn_inReturn);
        btnInClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void DialogSelectOptionIn() {
        final String items[] = { "미등록", "월급", "용돈", "꽁돈" };
        AlertDialog.Builder ab = new AlertDialog.Builder(cat_inc.this);
        ab.setTitle("카테고리");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inCategory = items[whichButton];
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

    private void DialogSelectOptionPayIn() {
        final String items[] = { "미등록", "카드", "현금", "통장" };
        AlertDialog.Builder ab = new AlertDialog.Builder(cat_inc.this);
        ab.setTitle("카테고리");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inMethod = items[whichButton];
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
        DatePickerDialog dpd = new DatePickerDialog(cat_inc.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(),year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+ "일", Toast.LENGTH_SHORT).show();
//                exYear = year;
                inMonth = monthOfYear+1;
                inDay = dayOfMonth;
                if(monthOfYear<9)
                    tempMonth = "0"+inMonth;
                else
                    tempMonth = ""+inMonth;

                if(inDay<10)
                    tempDate = "0"+inDay;
                else
                    tempDate = ""+inDay;

                dateString = ""+year+""+tempMonth+tempDate;

                dateInt = Integer.parseInt(dateString);
            }
        },2016,11,11);
        return dpd;
    }
}

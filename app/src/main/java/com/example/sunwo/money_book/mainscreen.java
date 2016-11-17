package com.example.sunwo.money_book;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

public class mainscreen extends AppCompatActivity {

    int type = 2;
    String Date[] = {"",""};
    final int DIALOG_START_DATE = 0;
    final int DIALOG_END_DATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        final DBExpense dbExpense = new DBExpense(getApplicationContext(), "money_ex.db", null, 1);
        final DBIncome dbIncome = new DBIncome(getApplicationContext(), "money_in.db", null, 1);

        ListView listview ;
        final ListViewAdapterEx adapter;

        // Adapter 생성
        adapter = new ListViewAdapterEx() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        Button btnSelect = (Button) findViewById(R.id.select_type);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogSelectType();
            }
        });

        Button btnSelectStartDate = (Button) findViewById(R.id.select_date_start);
        btnSelectStartDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_START_DATE);

            }
        });

        Button btnSelectEndDate = (Button) findViewById(R.id.select_date_end);
        btnSelectEndDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_END_DATE);
            }
        });

        Button btnFind = (Button) findViewById(R.id.find);
        btnFind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
  /*              if(type ==0){

                }
                else if(type == 1){

                }
                else{

                } */
                adapter.addItem("2만원", Date[0], "카테고리", "현금", "상세설명 어쩌구") ;
                adapter.addItem("3만원", Date[1], "카테고리", "현금", "상세설명 어쩌구") ;
                adapter.notifyDataSetChanged();
            }
        });

        // 첫 번째 아이템 추가.
        adapter.addItem("금액", "yyyy/mm/dd", "카테고리", "지불방법", "상세설명 어쩌구") ;

    }

    private void DialogSelectType() {
        final String items[] = { "지출", "수입" };
        AlertDialog.Builder ab = new AlertDialog.Builder(mainscreen.this);
        ab.setTitle("지출/수입");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        type = whichButton;
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

    protected Dialog onCreateDialog(final int id){
        DatePickerDialog dpd = new DatePickerDialog(mainscreen.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(),year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+ "일", Toast.LENGTH_SHORT).show();
                int temp = monthOfYear+1;
                Date[id] = ""+year+"년 "+temp+"월 "+dayOfMonth+"일";
            }
        },2016,11,11);
        return dpd;
    }
}

package com.example.sunwo.money_book;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class mainscreen extends AppCompatActivity {

    int type = 2;
    String typeString[] = {"","","미등록"};
    String dateTemp[] = {"",""};
    int Date[] = {20000000,20000000};
    final int DIALOG_START_DATE = 0;
    final int DIALOG_END_DATE = 1;
    int exMonth = 0;
    int exDay = 0;
    String tempMonth = "";
    String tempDate = "";
    String dateString = "";
    int dateInt = 20000000;
    int listViewCount = 0;
    TextView typeselected;
    TextView start_date;
    TextView end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        final DBExpense dbExpense = new DBExpense(getApplicationContext(), "money_ex.db", null, 1);
        final DBIncome dbIncome = new DBIncome(getApplicationContext(), "money_in.db", null, 1);

        ListView listview ;
        final ListViewAdapterEx adapter;

        final TextView testing = (TextView)findViewById(R.id.notyet1);
        typeselected = (TextView)findViewById(R.id.type1);
        start_date = (TextView)findViewById(R.id.start_date);
        end_date = (TextView)findViewById(R.id.end_date);

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
                if(type ==0){
                    adapter.deleteAll();
                    listViewCount = dbExpense.searchExpense(Date[0],Date[1]);
                    for(int i = 0;i<listViewCount;i++){
                        adapter.addItem(dbExpense.expenseStructs[i].getAmount(), dbExpense.expenseStructs[i].getDate(),
                                dbExpense.expenseStructs[i].getCategory(), dbExpense.expenseStructs[i].getMethod(), dbExpense.expenseStructs[i].getDescription()) ;
                    }
                }
                else if(type == 1){
                    adapter.deleteAll();
                    listViewCount = dbIncome.searchIncome(Date[0],Date[1]);
                    for(int i = 0;i<listViewCount;i++){
                        adapter.addItem(dbIncome.incomeStructs[i].getAmount(), dbIncome.incomeStructs[i].getDate(),
                                dbIncome.incomeStructs[i].getCategory(), dbIncome.incomeStructs[i].getMethod(), dbIncome.incomeStructs[i].getDescription()) ;
                    }
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "지출 또는 수입을 선택하시오", Toast.LENGTH_SHORT);
                    toast.show();
                }
                adapter.notifyDataSetChanged();
            }
        });

        // 첫 번째 아이템 추가.
        adapter.addItem("금액", "yyyy/mm/dd", "카테고리", "지불방법", "상세설명 어쩌구") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Listview_expense item = (Listview_expense) parent.getItemAtPosition(position) ;
                String description = item.getDescription();
                alert(description);
            }
        }) ;


    }

    private void DialogSelectType() {
        final String items[] = { "지출", "수입" };
        AlertDialog.Builder ab = new AlertDialog.Builder(mainscreen.this);
        ab.setTitle("지출/수입");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        type = whichButton;
                        typeString[type] = items[type];
                    }
                }).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        typeselected.setText(typeString[type]);
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
                dateTemp[id] = dateString;

                dateInt = Integer.parseInt(dateString);
                Date[id] = dateInt;

                if(id == 0)
                    start_date.setText(dateTemp[0]);
                else
                    end_date.setText(dateTemp[1]);
            }
        },2016,11,11);


        return dpd;
    }

    private void alert(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("상세설명")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}

package com.example.sunwo.money_book;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class inc_exp extends AppCompatActivity {
    EditText etAmount;
    Button division, plus, equal, multi, sub;
    Button cancel;
    String number, number2;
    int count = 0;
    int value;
    int ONE = -1;
    int DIVISION = 0;
    int PLUS = 1;
    int MULTI =  2;
    int SUB = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_exp);

        // DB에 저장 될 속성을 입력받는다
        etAmount = (EditText) findViewById(R.id.et_Amount);
//        final EditText etIncome = (EditText) findViewById(R.id.et_income);

        number = "";
        division = (Button) findViewById(R.id.btn_division);
        plus = (Button) findViewById(R.id.btn_plus);
        equal = (Button) findViewById(R.id.btn_result);
        sub = (Button) findViewById(R.id.btn_sub);
        multi = (Button) findViewById(R.id.btn_multi);

        division.setOnClickListener(mListener);
        plus.setOnClickListener(mListener);
        equal.setOnClickListener(mListener);
        sub.setOnClickListener(mListener);
        multi.setOnClickListener(mListener);



        cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number = "";
                etAmount.setText("");
            }
        });

        Button btn_insertEx = (Button) findViewById(R.id.btn_insertEx);
        btn_insertEx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String amount = etAmount.getText().toString();
                Intent intent = new Intent(inc_exp.this, cat_exp.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });


        Button btn_insertIn = (Button) findViewById(R.id.btn_insertIn);
        btn_insertIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String amount = etAmount.getText().toString();
                Intent intent = new Intent(inc_exp.this, cat_inc.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });
    }

    Button.OnClickListener mListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_division :
                    count++;
                    number = etAmount.getText().toString();
                    etAmount.setText("");
                    value = DIVISION;
                    Toast.makeText(inc_exp.this, "/", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_plus :
                    count++;
                    number = etAmount.getText().toString();
                    etAmount.setText("");
                    value = PLUS;
                    Toast.makeText(inc_exp.this, "+", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_sub :
                    count++;
                    number = etAmount.getText().toString();
                    etAmount.setText("");
                    value = SUB;
                    Toast.makeText(inc_exp.this, "-", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_multi :
                    count++;
                    number = etAmount.getText().toString();
                    etAmount.setText("");
                    value = MULTI;
                    Toast.makeText(inc_exp.this, "*", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_result :
                    number2 = etAmount.getText().toString();
                    if(count==0){
                        etAmount.setText(number2);
                    }
                    else {
                        if (value == MULTI) {
                            etAmount.setText("" + (Double.parseDouble(number) * Double.parseDouble(etAmount.getText().toString())));
                        } else if (value == SUB){
                            etAmount.setText("" + (Double.parseDouble(number) - Double.parseDouble(etAmount.getText().toString())));
                        } else if (value == PLUS){
                            etAmount.setText("" + (Double.parseDouble(number) + Double.parseDouble(etAmount.getText().toString())));
                        } else if (value == DIVISION){
                            etAmount.setText("" + (Double.parseDouble(number) / Double.parseDouble(etAmount.getText().toString())));
                        }
                        number = etAmount.getText().toString();
                        count = 0;
                    }
                    break;
            }
        }
    };

    public void onClick (View v)
    {
        switch(v.getId()){
            case R.id.btn_0 : etAmount.setText(etAmount.getText().toString() + 0); break;
            case R.id.btn_1 : etAmount.setText(etAmount.getText().toString() + 1); break;
            case R.id.btn_2 : etAmount.setText(etAmount.getText().toString() + 2); break;
            case R.id.btn_3 : etAmount.setText(etAmount.getText().toString() + 3); break;
            case R.id.btn_4 : etAmount.setText(etAmount.getText().toString() + 4); break;
            case R.id.btn_5 : etAmount.setText(etAmount.getText().toString() + 5); break;
            case R.id.btn_6 : etAmount.setText(etAmount.getText().toString() + 6); break;
            case R.id.btn_7 : etAmount.setText(etAmount.getText().toString() + 7); break;
            case R.id.btn_8 : etAmount.setText(etAmount.getText().toString() + 8); break;
            case R.id.btn_9 : etAmount.setText(etAmount.getText().toString() + 9); break;
        }
    }

}


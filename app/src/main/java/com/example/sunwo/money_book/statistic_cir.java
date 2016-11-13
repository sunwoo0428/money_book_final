package com.example.sunwo.money_book;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class statistic_cir extends AppCompatActivity {

    int date = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_cir);

        Button btnSelectDateCir = (Button) findViewById(R.id.btn_selectDateCir);
        btnSelectDateCir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogSelectOptionCirDate();
            }
        });
    }

    private void DialogSelectOptionCirDate() {
        final String items[] = { "1주", "1달"};
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.context);
        ab.setTitle("기간설정");
        ab.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(whichButton==0)
                            date = 7;
                        else if(whichButton==1)
                            date = 30;
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "기간이 선택되지 않았습니다", Toast.LENGTH_SHORT);
                            toast.show();
                        }
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
}

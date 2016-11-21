package com.example.sunwo.money_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sunwo on 2016-11-17.
 */
public class ListViewAdapterIn extends BaseAdapter {
    private ArrayList<Listview_income> listViewItemIn = new ArrayList<Listview_income>();

    public ListViewAdapterIn(){

    }

    public int getCount(){
        return listViewItemIn.size();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_income, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView moneyTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView dateTextView = (TextView) convertView.findViewById(R.id.textView2) ;
        TextView categoryTextView = (TextView) convertView.findViewById(R.id.textView3) ;
        TextView methodTextView = (TextView) convertView.findViewById(R.id.textView4) ;
        TextView desTextView = (TextView) convertView.findViewById(R.id.textView5) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Listview_income listViewItem = listViewItemIn.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        moneyTextView.setText(listViewItem.getIncome());
        dateTextView.setText(listViewItem.getDate());
        categoryTextView.setText(listViewItem.getCategory());
        methodTextView.setText(listViewItem.getMethod());
        desTextView.setText(listViewItem.getDescription());

        return convertView;
    }

    public long getItemId(int position) {
        return position ;
    }

    public Object getItem(int position) {
        return listViewItemIn.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String expense, String date, String category, String method, String description) {
        Listview_income item = new Listview_income();

        item.setIncome(expense);
        item.setDate(date);
        item.setCategory(category);
        item.setMethod(method);
        item.setDescription(description);

        listViewItemIn.add(item);
    }


}

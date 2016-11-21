package com.example.sunwo.money_book;

/**
 * Created by sunwo on 2016-11-17.
 */
public class Listview_income {
    private String income;
    private String date;
    private String category;
    private String method;
    private String description;

    public void setIncome(String income) {
        this.income = income;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public String getMethod() {
        return method;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getIncome() {
        return income;
    }



}

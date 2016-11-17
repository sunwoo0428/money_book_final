package com.example.sunwo.money_book;

/**
 * Created by sunwo on 2016-11-17.
 */
public class IncomeStruct {
    public String amount = "";
    public String date = "";
    public String category = "";
    public String method = "";
    public String description = "";

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getAmount() {

        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }

    public IncomeStruct(String amount, String date, String category, String method, String description) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.method = method;
        this.description = description;
    }
}

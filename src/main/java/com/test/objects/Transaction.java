package com.test.objects;

/**
 * Created by inal on 09.09.2016.
 */
public class Transaction {
    private long id;
    private double amount;
    private String data;

    public Transaction(long id, double amount, String data) {
        this.id = id;
        this.amount = amount;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", data='" + data + '\'' +
                '}';
    }
}

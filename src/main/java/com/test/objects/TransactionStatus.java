package com.test.objects;

import com.test.objects.Transaction;

/**
 * Created by inal on 09.09.2016.
 */
public class TransactionStatus {
    private Transaction transaction;
    private String status;

    public TransactionStatus(Transaction transaction, String status) {
        this.transaction = transaction;
        this.status = status;
    }

    public static String compareAndGetStatus(Transaction original, Transaction forCompare) {
        if (original == null) return "not found";
        if (Math.abs(original.getAmount()-forCompare.getAmount()) <= 0.000001) return "checked";
        return "uncorrect sum";
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionStatus{" +
                "transaction=" + transaction +
                ", status='" + status + '\'' +
                '}';
    }
}

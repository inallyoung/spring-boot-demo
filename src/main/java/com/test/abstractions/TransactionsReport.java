package com.test.abstractions;

import com.test.objects.TransactionStatus;

/**
 * Created by inal on 09.09.2016.
 */

public interface TransactionsReport extends Service {
    void sendTransaction(TransactionStatus transactionStatus);
}

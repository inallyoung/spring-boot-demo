package com.test.abstractions;

import com.test.objects.Transaction;
import com.test.objects.TransactionStatus;

/**
 * Created by inal on 09.09.2016.
 */
public interface DataBaseStorage extends Service {
    TransactionStatus getTransactionStatus(Transaction t);
}

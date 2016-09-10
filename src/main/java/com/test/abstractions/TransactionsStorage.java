package com.test.abstractions;

import com.test.objects.Transaction;

/**
 * Created by inal on 09.09.2016.
 */
public interface TransactionsStorage extends Service{
    Transaction next();
}

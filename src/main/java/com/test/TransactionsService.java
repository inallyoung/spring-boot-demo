package com.test;

import com.test.abstractions.DataBaseStorage;
import com.test.abstractions.TransactionsReport;
import com.test.abstractions.TransactionsStorage;
import com.test.objects.Transaction;
import com.test.objects.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by inal on 09.09.2016.
 */
@Component
public class TransactionsService {

    @Autowired
    private DataBaseStorage dataBaseStorage;
    @Autowired
    private TransactionsStorage transactionsStorage;
    @Autowired
    private TransactionsReport transactionsReport;

    public void execute() throws Exception {

        ServiceRegister serviceRegister = new ServiceRegister();
        serviceRegister.register(dataBaseStorage);
        serviceRegister.register(transactionsStorage);
        serviceRegister.register(transactionsReport);

        try {
            serviceRegister.init();

            Transaction t;
            while ((t = transactionsStorage.next()) != null) {
                TransactionStatus transactionStatus = dataBaseStorage.getTransactionStatus(t);
                transactionsReport.sendTransaction(transactionStatus);
            }

        } finally {
            serviceRegister.stop();
        }
    }
}

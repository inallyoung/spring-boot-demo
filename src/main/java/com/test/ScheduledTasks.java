package com.test;

/**
 * Created by inal on 10.09.2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    TransactionsService transactionService;

    @Scheduled(fixedRate = 60000)
    public void execute() throws Exception {
        transactionService.execute();
    }
}

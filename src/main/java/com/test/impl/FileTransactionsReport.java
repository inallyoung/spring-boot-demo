package com.test.impl;

import com.test.abstractions.TransactionsReport;
import com.test.objects.TransactionStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by inal on 10.09.2016.
 */
@Component
public class FileTransactionsReport implements TransactionsReport {

    @Value("${transactions.filename}")
    private String fileName;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");

    public FileTransactionsReport() {
        System.out.println("FileTransactionsReport initialized");
    }

    private BufferedWriter writer;

    @Override
    public void init() throws Exception {
        String date = dateFormat.format(new Date());
        Path path = Paths.get(new File(fileName).getParent() + File.separator+"report."+date+".txt");
        writer = Files.newBufferedWriter(path);
    }

    @Override
    public void stop() throws Exception {
        if (writer != null)
            writer.close();
    }


    @Override
    public void sendTransaction(TransactionStatus transactionStatus) {
        try {
            writer.write(transactionStatus.getTransaction().getId() + " " +transactionStatus.getStatus());
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

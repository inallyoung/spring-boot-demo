package com.test.impl;

import com.test.abstractions.TransactionsStorage;
import com.test.objects.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by inal on 09.09.2016.
 */
@Component
public class FileTransactionsStorage implements TransactionsStorage {

    @Value("${transactions.filename}")
    private String fileName;

    public FileTransactionsStorage() {
        System.out.println("FileTransactionsStorage initialized");
    }

    private BufferedReader reader;

    public void init() throws IOException {
        Path path = Paths.get(fileName);
        reader = Files.newBufferedReader(path);
        // skip first line
        reader.readLine();
    }

    @Override
    public void stop() throws Exception {
        if (reader != null)
            reader.close();
    }

    @Override
    public Transaction next() {
        String s = null;
        try {
            s = reader.readLine();
            if (s == null) return null;
            String[] fields = s.split(";");
            if ("TOTAL".equals(fields[0])) {
                reader.close();
                return null;
            }
            return new Transaction(Long.valueOf(fields[0]), Double.valueOf(fields[1]), fields[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

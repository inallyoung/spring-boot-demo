package com.test.impl;

import com.test.abstractions.DataBaseStorage;
import com.test.objects.Transaction;
import com.test.objects.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by inal on 09.09.2016.
 */
@Component
public class PostgresDataBaseStorage implements DataBaseStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PostgresDataBaseStorage() {
        System.out.println("PostgresDataBaseStorage initialized");
    }

    @Override
    public TransactionStatus getTransactionStatus(Transaction transaction) {
        Transaction transactionOriginal = findTransactionById(transaction.getId());
        String status = TransactionStatus.compareAndGetStatus(transactionOriginal, transaction);
        return new TransactionStatus(transaction, status);
    }

    @Override
    public void init() throws Exception {
        System.out.println("DataBase prepare for getting selects");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("DataBase successfully end.");
    }

    @Transactional(readOnly=true)
    public List<Transaction> findAll() {
        return jdbcTemplate.query("select * from transactions",
                new TransactionRowMapper());
    }

    @Transactional(readOnly=true)
    public Transaction findTransactionById(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from transactions where id=?",
                    new Object[]{id}, new TransactionRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}


class TransactionRowMapper implements RowMapper<Transaction>
{
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction t = new Transaction(
                rs.getLong("id"),
                rs.getDouble("amount"),
                rs.getString("data"));
        return t;
    }
}
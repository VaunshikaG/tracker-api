package com.tapi.trackerapi.Tracker.repository.Transaction;
import com.tapi.trackerapi.Tracker.domain.Transaction;
import com.tapi.trackerapi.Tracker.exceptions.TBadRequestException;
import com.tapi.trackerapi.Tracker.exceptions.TResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    private static final String SQL_CREATE = "INSERT INTO T_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID, " +
            "AMOUNT, NOTE, TRANSACTION_DATE) VALUES(NEXTVAL('T_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, " +
            "TRANSACTION_DATE FROM T_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findall(Integer userId) throws TResourceNotFoundException {
        return null;
    }

    @Override
    public Transaction findById(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId, transactId}, TransactionRowMapper);
        } catch (Exception e) {
            throw new TResourceNotFoundException("Transaction not found");
        }
    }

    @Override
    public Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactDate) throws TBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setInt(2, categoryId);
                ps.setDouble(3, amount);
                ps.setString(4, note);
                ps.setLong(5, transactDate);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("TRANSACTION_ID");
        } catch (Exception e) {
            throw new TBadRequestException("Invalid Request");
        }
    }

    @Override
    public void update(Integer userId, Integer categoryId, Integer transactId, Transaction transaction) throws TBadRequestException {

    }

    @Override
    public void deleteById(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException {

    }

    //  to map in rows & cols
    private final RowMapper<Transaction> TransactionRowMapper = (((rs, rowNum) -> {
        return new Transaction(
                rs.getInt("USER_ID"),
                rs.getInt("CATEGORY_ID"),
                rs.getInt("TRANSACTION_ID"),
                rs.getDouble("AMOUNT"),
                rs.getString("NOTE"),
                rs.getLong("TRANSACTION_DATE")
        );
    }));
}

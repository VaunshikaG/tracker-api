package com.tapi.trackerapi.Tracker.services.Transaction;
import com.tapi.trackerapi.Tracker.domain.Transaction;
import com.tapi.trackerapi.Tracker.exceptions.TBadRequestException;
import com.tapi.trackerapi.Tracker.exceptions.TResourceNotFoundException;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchallTransaction(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException;

    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactDate) throws TBadRequestException;

    void updateTransaction(Integer userId, Integer categoryId, Integer transactId,Transaction transaction) throws TBadRequestException;

    void deleteTransaction(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException;
}

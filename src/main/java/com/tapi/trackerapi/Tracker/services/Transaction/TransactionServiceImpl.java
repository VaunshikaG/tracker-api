package com.tapi.trackerapi.Tracker.services.Transaction;
import com.tapi.trackerapi.Tracker.domain.Transaction;
import com.tapi.trackerapi.Tracker.exceptions.TBadRequestException;
import com.tapi.trackerapi.Tracker.exceptions.TResourceNotFoundException;
import com.tapi.trackerapi.Tracker.repository.Transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> fetchallTransaction(Integer userId, Integer categoryId) {
        return null;
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException {
        return null;
    }

    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactDate) throws TBadRequestException {
        int transactId = transactionRepository.create(userId, categoryId, amount, note, transactDate);
        return transactionRepository.findById(userId, categoryId, transactId);
    }

    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactId, Transaction transaction) throws TBadRequestException {

    }

    @Override
    public void deleteTransaction(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException {

    }
}

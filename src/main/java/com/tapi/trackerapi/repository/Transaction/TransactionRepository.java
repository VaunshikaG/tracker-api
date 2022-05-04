package com.tapi.trackerapi.repository.Transaction;
import com.tapi.trackerapi.domain.Transaction;
import com.tapi.trackerapi.exceptions.TBadRequestException;
import com.tapi.trackerapi.exceptions.TResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findall(Integer userId) throws TResourceNotFoundException;

    Transaction findById(Integer userId, Integer categoryId, Integer transactId) throws TResourceNotFoundException;

    Integer create(Integer  userId, Integer categoryId, Double amount, String note, Long transactDate) throws TBadRequestException;

    void update(Integer  userId, Integer categoryId, Integer transactId, Transaction transaction) throws TBadRequestException;

    void deleteById(Integer userId, Integer categoryId,Integer transactId) throws TResourceNotFoundException;

}

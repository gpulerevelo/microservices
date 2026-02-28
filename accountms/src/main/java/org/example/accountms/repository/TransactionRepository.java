package org.example.accountms.repository;

import org.example.accountms.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountIdOrderByDateDesc(Long accountId);
    List<Transaction> findByAccountIdInAndDateBetween(List<Long> accountIds, Date start, Date end);
}

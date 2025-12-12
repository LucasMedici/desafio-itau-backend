package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    // Usar uma ConcurrentLinkedQueue garante o Thread-safety (Com JPA ele cuidaria disso)
    private final Queue<Transaction> transactionsQueue = new ConcurrentLinkedQueue<>();

    public void addQueue(Transaction transaction){
        transactionsQueue.add(transaction);
    }

    public void clearQueue(){
        transactionsQueue.clear();
    }

    public List<Transaction> findByLast60Seconds(){
        OffsetDateTime limit = OffsetDateTime.now().minusSeconds(60);
        return transactionsQueue.stream()
                .filter(transaction ->
                    transaction.getDataHora().isAfter(limit)
                )
                .toList();
    }
}

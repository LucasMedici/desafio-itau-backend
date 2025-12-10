package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

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
}

package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Value("${time.to.get.statistics}")
    private Long timeToGetStatistics;


    // Usar uma ConcurrentLinkedQueue garante o Thread-safety (Com JPA ele cuidaria disso)
    private final Queue<Transaction> transactionsQueue = new ConcurrentLinkedQueue<>();

    public void addQueue(Transaction transaction){
        logger.info("Adicionando a transaction à Fila: valor={}, dataHora={}", transaction.getValor(), transaction.getDataHora());
        transactionsQueue.add(transaction);
    }

    public void clearQueue(){
        logger.info("Limpando a fila");
        transactionsQueue.clear();
    }

    public List<Transaction> findByLastSeconds(){
        OffsetDateTime limit = OffsetDateTime.now().minusSeconds(timeToGetStatistics);
        logger.info("Buscando transactions que ocorreram de {} até {}", limit, OffsetDateTime.now());
        return transactionsQueue.stream()
                .filter(transaction ->
                    transaction.getDataHora().isAfter(limit)
                )
                .toList();
    }
}

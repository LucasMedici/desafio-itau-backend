package desafio.itau.springboot.service;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Queue;

@Service
public class StatisticsService {

    private TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    public StatisticsService(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public StatisticsDTO getStatistics() {
        List<Transaction> last60SecondsTransactions = transactionService.findByLast60Seconds();
        logger.info("Calculando estatísticas | totalTransactions={}", last60SecondsTransactions.size());
        DoubleSummaryStatistics stats = last60SecondsTransactions.stream()
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
        if(stats.getCount() == 0){
            return new StatisticsDTO(0,0.0,0.0,0.0,0.0);
        }
        return new StatisticsDTO(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }


}

package desafio.itau.springboot.service;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.model.Transaction;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsService {

    private TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    public StatisticsService(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @Timed(value = "statistics.calculation.time", description = "Métricas relacionados ao calculo das estatísticas")
    public StatisticsDTO getStatistics() {
        List<Transaction> lastSecondsTransactions = transactionService.findByLastSeconds();
        logger.info("Calculando estatísticas | totalTransactions={}", lastSecondsTransactions.size());
        DoubleSummaryStatistics stats = lastSecondsTransactions.stream()
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
        if(stats.getCount() == 0){
            return new StatisticsDTO(0,0.0,0.0,0.0,0.0);
        }
        return new StatisticsDTO(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }


}

package desafio.itau.springboot.service;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Queue;

@Service
public class StatisticsService {

    private TransactionService transactionService;

    public StatisticsService(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public StatisticsDTO getStatistics() {
        List<Transaction> last60SecondsTransactions = transactionService.findByLast60Seconds();
        DoubleSummaryStatistics stats = last60SecondsTransactions.stream()
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
        System.out.println(stats.getCount());
        if(stats.getCount() == 0){
            return new StatisticsDTO(0,0.0,0.0,0.0,0.0);
        }
        return new StatisticsDTO(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }


}

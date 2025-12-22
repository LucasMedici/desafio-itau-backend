package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {
    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private TransactionService transactionService;

    @Nested
    class GetStatistics {
        // lista vazia = valores com 0
        // conferir valores retorandos sao iguais a valores reais

        @Test
        void shouldReturnZeroStatisticsWhenNoTransactionsExist(){

            when(transactionService.findByLastSeconds())
                    .thenReturn(Collections.emptyList());

            var result = statisticsService.getStatistics();

            assertEquals(0, result.count());
            assertEquals(0.0, result.sum());
            assertEquals(0.0, result.avg());
            assertEquals(0.0, result.min());
            assertEquals(0.0, result.max());
            verify(transactionService, times(1)).findByLastSeconds();

        }

        @Test
        void shouldReturnCorrectStatisticsValues(){
            List<Transaction> transactionsList = List.of(
              new Transaction(20, OffsetDateTime.now()),
              new Transaction(30, OffsetDateTime.now()),
              new Transaction(40, OffsetDateTime.now())
            );
            when(transactionService.findByLastSeconds()).thenReturn(transactionsList);

            var result = statisticsService.getStatistics();

            assertEquals(3, result.count());
            assertEquals(90.0, result.sum());
            assertEquals(30.0, result.avg());
            assertEquals(20.0, result.min());
            assertEquals(40.0, result.max());
            verify(transactionService, times(1)).findByLastSeconds();


        }




    }

}
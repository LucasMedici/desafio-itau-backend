package desafio.itau.springboot.service;

import desafio.itau.springboot.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void setup() throws Exception{
        transactionService = new TransactionService();

        var field = TransactionService.class.getDeclaredField("timeToGetStatistics");

        field.setAccessible(true);
        field.set(transactionService, 60L);
    }

    @Nested
    class AddQueue {

        @Test
        void shouldReturnTransactionFromLast60Seconds(){
            var transaction = new Transaction(20, OffsetDateTime.now());

            transactionService.addQueue(transaction);

            var listByLast60Seconds = transactionService.findByLastSeconds();

            assertEquals(1, listByLast60Seconds.size());
            assertTrue(listByLast60Seconds.contains(transaction));

        }

        @Test
        void shouldNotReturnTransactionOlderThan60Seconds(){
            var transaction = new Transaction(20, OffsetDateTime.now().minusSeconds(500));

            transactionService.addQueue(transaction);

            var listByLast60Seconds = transactionService.findByLastSeconds();

            assertFalse(listByLast60Seconds.contains(transaction));
        }

        @Test
        void shouldReturnEmptyList(){
            var listByLast60Seconds = transactionService.findByLastSeconds();
            assertTrue(listByLast60Seconds.isEmpty());
        }
    }

    @Nested
    class ClearQueue {

        @Test
        void shouldClearAllTransactionsFromQueue(){
            var transaction1 = new Transaction(20, OffsetDateTime.now());
            var transaction2 = new Transaction(40, OffsetDateTime.now());

            transactionService.addQueue(transaction1);
            transactionService.addQueue(transaction2);
            transactionService.clearQueue();
            var listByLast60Seconds = transactionService.findByLastSeconds();

            assertTrue(listByLast60Seconds.isEmpty());


        }
    }

    @Nested
    class FindByLast60Seconds {

        @Test
        void shouldReturnOnlyTransactionsFromLast60Seconds(){
            var t1 = new Transaction(10, OffsetDateTime.now());
            var t2 = new Transaction(20, OffsetDateTime.now().minusSeconds(20));
            var t3 = new Transaction(30, OffsetDateTime.now().minusSeconds(40));
            var t4 = new Transaction(4, OffsetDateTime.now().minusSeconds(70));
            var t5 = new Transaction(4, OffsetDateTime.now().minusSeconds(120));

            transactionService.addQueue(t1);
            transactionService.addQueue(t2);
            transactionService.addQueue(t3);
            transactionService.addQueue(t4);
            transactionService.addQueue(t5);
            var listByLast60Seconds = transactionService.findByLastSeconds();

            assertTrue(listByLast60Seconds.contains(t1));
            assertTrue(listByLast60Seconds.contains(t2));
            assertTrue(listByLast60Seconds.contains(t3));
            assertFalse(listByLast60Seconds.contains(t4));
            assertFalse(listByLast60Seconds.contains(t5));
        }

    }

}
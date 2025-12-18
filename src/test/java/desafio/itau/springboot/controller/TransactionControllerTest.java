package desafio.itau.springboot.controller;

import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransactionService transactionService;

    @Nested
    class AddTransaction {

        @Test
        void shoudReturnHttp201IfTransactionAceptted() throws Exception {
            var transaction = new Transaction(20, OffsetDateTime.now());
            var requestBody = objectMapper.writeValueAsString(transaction);

            mockMvc.perform(post("/transacao")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(content().string(""));

            verify(transactionService, times(1)).addQueue(any(Transaction.class));
        }

        @Test
        void shouldReturnHttp422IfTransactionNotAceptted() throws Exception {
            var transaction = new Transaction(-20, OffsetDateTime.now());
            var requestBody = objectMapper.writeValueAsString(transaction);

            mockMvc.perform(post("/transacao")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(content().string(""));

            verify(transactionService, times(0)).addQueue(any(Transaction.class));

        }

        @Test
        void shouldReturn400IfRequestBodyIsInvalidJson() throws Exception {
            var invalidJson = "{ \"valor\": 10 ";

            mockMvc.perform(post("/transacao")
                            .content(invalidJson)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(""));

            verify(transactionService, never()).addQueue(any(Transaction.class));
        }

    }

    @Nested
    class ClearTransaction {

        @Test
        void shoudReturnHttp200() throws Exception {
            mockMvc.perform(delete("/transacao"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
            verify(transactionService, times(1)).clearQueue();

        }
    }
}
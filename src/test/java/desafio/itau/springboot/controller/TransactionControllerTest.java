package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.TransactionDTO;
import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    class AddTransaction{

        @Test
        void shoudReturnHttp201IfTransactionAceptted() throws Exception {
            var dto = new Transaction(20, OffsetDateTime.now());
            doNothing().when(transactionService).addQueue(dto);
            var requestBody = objectMapper.writeValueAsString(dto);

            mockMvc.perform(post("/transacao")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated());
        }

        @Test
        void shouldReturnHttp422IfTransactionNotAceptted() {

        }
    }

    @Nested
    class ClearTransaction{

        @Test
        void shoudReturnHttp200OK() {
        }

        @Test
        void shoudReturnEmptyBody() {
        }
    }


}
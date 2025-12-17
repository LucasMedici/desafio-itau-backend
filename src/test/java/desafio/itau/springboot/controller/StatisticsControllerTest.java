package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.service.StatisticsService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StatisticsControllerTest {

    @Mock
    StatisticsService statisticsService;
    // aqui basicamente estamos declarando os mocks necessários e implementando eles no nosso controller que será testado
    @InjectMocks
    StatisticsController statisticsController;


    // criar uma sub-classe para dividir os testes, nesse caso nessa classe só teriam testes relacionados ao metodo getStatistics
    // O @Nested indica que é uma sub-classe que PODE receber testes
    @Nested
    class GetStatistics {

        @Test
        void shouldReturnHttpOK() {
//            //metodo triple A:
//            //ARRANGE - prepara todos os mocks para execução
//            // nesse caso nenhum parametro é passado para rodar o metodo
//
//            // retorne X quando staticsService chamar getStatistics
//            doReturn(new StatisticsDTO(5, 4.0, 4.0 ,4.0, 5.0)).when(statisticsService).getStatistics();
//
//            // ACT - executar o metodo a ser testado
//            var response = statisticsController.getStatistics();
//
//            // ASSERT - verifica se a execução foi certinha
//            assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
//            var dto = new StatisticsDTO(5, 4.0, 4.0 ,4.0, 5.0);
//            doReturn(dto).when(statisticsService).getStatistics();
//            var response = statisticsController.getStatistics();
//            assertNotNull(response.getBody());
//            assertNotNull(response.getBody().sum());
//            assertNotNull(response.getBody().avg());
//            assertNotNull(response.getBody().min());
//            assertNotNull(response.getBody().max());
//
//            assertEquals(response.getBody().count(), dto.count());
//            assertEquals(response.getBody().sum(), dto.sum());
//            assertEquals(response.getBody().avg(), dto.avg());
//            assertEquals(response.getBody().min(), dto.min());
//            assertEquals(response.getBody().max(), dto.max());
        }
    }        


}
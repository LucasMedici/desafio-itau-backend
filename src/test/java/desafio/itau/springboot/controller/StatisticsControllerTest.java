package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.service.StatisticsService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatisticsController.class)
class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StatisticsService statisticsService;


    @Nested
    class GetStatistics {

        @Test
        void shouldReturnHttpOK() throws Exception{
            var dto = new StatisticsDTO(20, 2.0,20.0,20.0,20.0);

            when(statisticsService.getStatistics()).thenReturn(dto);

            mockMvc.perform(get("/estatistica"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.count").exists())
                    .andExpect(jsonPath("$.sum").exists())
                    .andExpect(jsonPath("$.avg").exists())
                    .andExpect(jsonPath("$.min").exists())
                    .andExpect(jsonPath("$.max").exists());
            verify(statisticsService, times(1)).getStatistics();
        }
    }        


}
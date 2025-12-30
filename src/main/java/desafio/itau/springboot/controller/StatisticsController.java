package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estatistica")
@Tag(name = "Estatísticas", description = "Operações relacionadas a estatísticas")
public class StatisticsController {

    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @Operation(summary = "Busca estatísticas das transações nos últimos segundos configurados", method = "GET")
    @ApiResponse(responseCode = "200", description = "Dados das estatísticas conforme tempo configurado")
    @GetMapping
    public ResponseEntity<StatisticsDTO> getStatistics(){
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getStatistics());
    }
}

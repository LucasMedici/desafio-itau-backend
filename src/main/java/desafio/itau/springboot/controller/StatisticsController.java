package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.StatisticsDTO;
import desafio.itau.springboot.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estatistica")
public class StatisticsController {

    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<StatisticsDTO> getStatistics(){
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getStatistics());
    }
}

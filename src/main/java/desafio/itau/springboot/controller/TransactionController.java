package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.TransactionDTO;
import desafio.itau.springboot.exception.ValorCannotBeLessThanZeroException;
import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@Tag(name = "Transações", description = "Operações relacionadas a transações")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @Operation(summary = "Adiciona uma transação na queue", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A transação foi aceita"),
            @ApiResponse(responseCode = "422", description = "A transação não foi aceita"),
            @ApiResponse(responseCode = "400", description = "A API não compreendeu a requisição do cliente")
    })
    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody @Valid TransactionDTO transactionDTO){
        if(transactionDTO.valor()<0){
            throw new ValorCannotBeLessThanZeroException("O valor não pode ser menor que zero.");
        }
        transactionService.addQueue(new Transaction(transactionDTO.valor(), transactionDTO.dataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Limpa a fila de transações", method = "DELETE")
    @ApiResponse(responseCode = "200", description = "A fila foi limpa com sucesso")
    @DeleteMapping
    public ResponseEntity<Void> clearTransaction(){
        transactionService.clearQueue();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

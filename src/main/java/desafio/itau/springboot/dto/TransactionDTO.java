package desafio.itau.springboot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record TransactionDTO (
        @NotNull
        double valor,
        @NotNull
        @Past
        OffsetDateTime dataHora) {
}

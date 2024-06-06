package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record PeriodoRequestDTO(
        @NotNull(message = "Escolha um Periodo")
        String tipo,
        String dataPeriodo
) {

}



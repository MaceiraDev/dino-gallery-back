package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record FiloRequestDTO(
        @NotNull(message = " Selecione o Filo")
        String tipo
) {
}

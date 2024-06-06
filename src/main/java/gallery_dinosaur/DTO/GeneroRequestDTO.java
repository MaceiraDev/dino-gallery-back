package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

public record GeneroRequestDTO(
        @NotNull(message = " Selecione o Genero")
        String tipo
) {
}

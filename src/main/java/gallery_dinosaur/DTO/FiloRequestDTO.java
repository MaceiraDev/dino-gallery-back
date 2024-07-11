package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record FiloRequestDTO(
        @NotNull(message = "Filo é obrigatório")
        String tipo
) {
}

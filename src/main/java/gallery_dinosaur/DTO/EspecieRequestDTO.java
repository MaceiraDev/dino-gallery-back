package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

public record EspecieRequestDTO(
        @NotNull(message = "Selecione um tipo de Especie")
        String tipo
) {
}

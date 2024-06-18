package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record DietaRequestDTO(
        @NotNull(message = "O tipo de Dieta não pode ser nulo")
        String tipo
) {
}

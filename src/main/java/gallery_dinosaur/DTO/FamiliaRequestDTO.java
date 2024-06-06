package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record FamiliaRequestDTO(
        @NotNull(message = "O tipo de Familia não pode ser nulo")
        String tipo
) {

}

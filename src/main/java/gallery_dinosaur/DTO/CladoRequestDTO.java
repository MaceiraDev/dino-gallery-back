package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

public record CladoRequestDTO(
        @NotNull(message = "O tipo de Clado não pode ser nulo")
        String tipo
) {

}

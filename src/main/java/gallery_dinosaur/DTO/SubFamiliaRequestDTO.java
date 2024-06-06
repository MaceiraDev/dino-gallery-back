package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

public record SubFamiliaRequestDTO(
        @NotNull(message = "O tipo de SubFamilia não pode ser nulo")
        String tipo
) {

}

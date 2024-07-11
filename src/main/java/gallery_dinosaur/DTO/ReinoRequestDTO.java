package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record ReinoRequestDTO(
        @NotNull(message = "Reino não pode ser nulo")
        String tipo
) {

}
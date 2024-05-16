package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record CladoRequestDTO(
        @NotNull(message = "O tipo de Clado não pode ser nulo")
        @Pattern(regexp = " |DINOSSAURIA", message = "Escolha o tipo de clado  'DINOSSAURIA'")
        String tipo
) {

}

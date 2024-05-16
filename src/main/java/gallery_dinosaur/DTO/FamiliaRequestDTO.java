package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record FamiliaRequestDTO(
        @NotNull(message = "O tipo de Familia não pode ser nulo")
        @Pattern(regexp = " |X", message = "Escolha O tipo de familia deve ser 'X'")
        String tipo
) {

}

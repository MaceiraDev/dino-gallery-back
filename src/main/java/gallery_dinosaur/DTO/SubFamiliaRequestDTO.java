package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record SubFamiliaRequestDTO(
        @NotNull(message = "O tipo de SubFamilia não pode ser nulo")
        @Pattern(regexp = " |X", message = "Escolha O tipo de Subfamilia deve ser 'X'")
        String tipo
) {

}

package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record EspecieRequestDTO(
        @NotNull(message = " Selecione um tipo de Especie")
        @Pattern(regexp = "X|", message = "Escolha o tipo de Especie 'X'")
        String tipo
) {
}

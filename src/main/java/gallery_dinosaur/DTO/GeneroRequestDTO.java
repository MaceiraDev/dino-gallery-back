package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record GeneroRequestDTO(
        @NotNull(message = " Selecione o Genero")
        @Pattern(regexp = "FEM|MASC", message = "Escolha o genero 'FEM', 'MASC' ")
        String tipo
) {
}

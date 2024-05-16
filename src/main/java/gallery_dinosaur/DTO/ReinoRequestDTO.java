package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record ReinoRequestDTO(
        @NotNull(message = "Escolha um Reino")
        @Pattern(regexp = "CRETACEO|TRIASSICO|JURASSICO", message = "O tipo de reino deve ser 'CRETACEO', 'TRIASSICO' ou 'JURASSICO'")
        String tipo
) {

}
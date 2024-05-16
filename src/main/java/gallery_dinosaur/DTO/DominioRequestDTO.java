package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record DominioRequestDTO(
        @NotNull(message = " Selecione um tipo de Dominio")
        @Pattern(regexp = "X|Y|Z", message = "Escolha o tipo de locomoção 'X', 'Y' ou 'Z'")
        String tipo
) {

}

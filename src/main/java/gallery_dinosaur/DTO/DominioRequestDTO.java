package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record DominioRequestDTO(
        @NotNull(message = " Selecione um tipo de Dominio")
        String tipo
) {

}

package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record MetodoLocomocaoRequestDTO(
        @NotNull(message = " Selecione um tipo de Metodo de Locomoção")
        String tipo
) {

}

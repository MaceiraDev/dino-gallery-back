package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record ReinoRequestDTO(
        @NotNull(message ="Escolha um Reino")
        String tipo,
        String dataReino
) {

}
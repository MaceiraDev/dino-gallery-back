package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;


public record FilmeRequestDTO(

        @NotNull(message = "O nome do filme não deve ser nulo")
        String nome,
        String dtl,
        String urn,
        String genero,
        String sinopse
) {
}

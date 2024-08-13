package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record FilmeRequestDTO(
        @NotNull(message = "O nome do filme não deve ser nulo")

        String nome,
        Date dtl,
        String urn,
        String genero,
        String sinopse
) {
}

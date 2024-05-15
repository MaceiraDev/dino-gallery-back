package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Clado;

public record CladoResponseDTO(Long id, String tipo) {
    public CladoResponseDTO(Clado clado) {
        this(clado.getId(), clado.getTipo());
    }
}

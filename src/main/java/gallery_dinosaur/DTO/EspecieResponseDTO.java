package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Especie;

public record EspecieResponseDTO(Long id, String tipo) {
    public EspecieResponseDTO(Especie especie) {
        this(especie.getId(), especie.getTipo());
    }
}

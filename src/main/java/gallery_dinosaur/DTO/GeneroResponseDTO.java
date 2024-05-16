package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Genero;

public record GeneroResponseDTO(Long id, String tipo) {
    public GeneroResponseDTO(Genero genero) {
        this(genero.getId(), genero.getTipo());
    }

}

package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Dominio;

public record DominioResponseDTO(Long id, String tipo) {
    public DominioResponseDTO(Dominio dominio) {
        this(dominio.getId(), dominio.getTipo());
    }
}


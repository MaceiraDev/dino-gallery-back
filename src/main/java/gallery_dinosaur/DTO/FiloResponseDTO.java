package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Filo;

public record FiloResponseDTO(Long id, String tipo) {
    public FiloResponseDTO(Filo filo) {
        this(filo.getId(), filo.getTipo());
    }

}

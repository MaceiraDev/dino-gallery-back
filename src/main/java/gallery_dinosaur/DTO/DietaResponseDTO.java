package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Dieta;

public record DietaResponseDTO(Long id, String tipo) {
    public DietaResponseDTO(Dieta dieta) {
        this(dieta.getId(), dieta.getTipo());
    }
}
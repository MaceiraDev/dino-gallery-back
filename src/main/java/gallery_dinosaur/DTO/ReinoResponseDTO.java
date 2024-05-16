package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Reino;


public record ReinoResponseDTO(Long id, String tipo, String dataReino) {
    public ReinoResponseDTO(Reino reino) {
        this(reino.getId(), reino.getTipo(), reino.getDataReino());
    }
}

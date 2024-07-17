package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Mezozoico;

public record MezozoicoResponseDTO(Long id) {
    public MezozoicoResponseDTO(Mezozoico mezozoico) {
        this(mezozoico.getId());
    }

}

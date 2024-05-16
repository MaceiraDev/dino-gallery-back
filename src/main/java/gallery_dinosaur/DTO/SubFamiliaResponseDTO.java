package gallery_dinosaur.DTO;

import gallery_dinosaur.model.SubFamilia;

public record SubFamiliaResponseDTO(Long id, String tipo) {
    public SubFamiliaResponseDTO(SubFamilia subFamilia) {
        this(subFamilia.getId(), subFamilia.getTipo());
    }
}

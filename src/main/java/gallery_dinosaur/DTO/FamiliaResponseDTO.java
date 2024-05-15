package gallery_dinosaur.DTO;


import gallery_dinosaur.model.Familia;

public record FamiliaResponseDTO(Long id, String tipo) {
    public FamiliaResponseDTO(Familia familia) {
        this(familia.getId(), familia.getTipo());
    }
}

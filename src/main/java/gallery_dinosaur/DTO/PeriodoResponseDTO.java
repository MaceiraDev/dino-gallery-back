package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Periodo;

public record PeriodoResponseDTO(Long id, String tipo) {
    public PeriodoResponseDTO(Periodo periodo) {
        this(periodo.getId(), periodo.getTipo());
    }
}
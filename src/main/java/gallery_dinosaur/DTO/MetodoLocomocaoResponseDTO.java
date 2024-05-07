package gallery_dinosaur.DTO;

import gallery_dinosaur.model.MetodoLocomocao;

public record MetodoLocomocaoResponseDTO(Long id, String tipo) {
    public MetodoLocomocaoResponseDTO(MetodoLocomocao metodolocomocao) {
        this(metodolocomocao.getId(), metodolocomocao.getTipo());
    }
}
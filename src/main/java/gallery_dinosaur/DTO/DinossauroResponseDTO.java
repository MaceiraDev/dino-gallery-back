package gallery_dinosaur.DTO;

import gallery_dinosaur.model.*;

public record DinossauroResponseDTO(
        Long id,
        String nome,
        Float tamanho,
        Float peso,
        String dieta_principal,
        String habitat_natural,
        Long clado,
        Long dieta,
        Long dominio,
        Long especie,
        Long familia,
        Long filo,
        Long genero,
        Long metodoLocomocao,
        Long periodo,
        Long reino,
        Long subFamilia
        ) {
    public DinossauroResponseDTO(Dinossauro dinossauro) {
        this(
                dinossauro.getId(),
                dinossauro.getNome(),
                dinossauro.getTamanho(),
                dinossauro.getPeso(),
                dinossauro.getDieta_principal(),
                dinossauro.getHabitat_natural(),
                dinossauro.getClado() != null ? dinossauro.getClado().getId() : null,
                dinossauro.getDieta() != null ? dinossauro.getDieta().getId() : null,
                dinossauro.getDominio() != null ? dinossauro.getDominio().getId() : null,
                dinossauro.getEspecie() != null ? dinossauro.getEspecie().getId() : null,
                dinossauro.getFamilia() != null ? dinossauro.getFamilia().getId() : null,
                dinossauro.getFilo() != null ? dinossauro.getFilo().getId() : null,
                dinossauro.getGenero() != null ? dinossauro.getGenero().getId() : null,
                dinossauro.getMetodoLocomocao() != null ? dinossauro.getMetodoLocomocao().getId() : null,
                dinossauro.getPeriodo() != null ? dinossauro.getPeriodo().getId() : null,
                dinossauro.getReino() != null ? dinossauro.getReino().getId() : null,
                dinossauro.getSubFamilia() != null ? dinossauro.getSubFamilia().getId() : null
                );
    }
}

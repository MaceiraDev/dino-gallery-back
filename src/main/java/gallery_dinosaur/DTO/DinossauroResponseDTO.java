package gallery_dinosaur.DTO;

import gallery_dinosaur.model.*;

public record DinossauroResponseDTO(
        Long id,
        String nome,
        Float tamanho,
        Float peso,
        String dietaPrincipal,
        String habitatNatural,
        Long cladoId,
        Long dietaId,
        Long dominioId,
        Long especieId,
        Long familiaId,
        Long filoId,
        Long generoId,
        Long metodoLocomocaoId,
        Long periodoId,
        Long reinoId,
        Long subFamiliaId
        ) {
    public DinossauroResponseDTO(Dinossauro dinossauro) {
        this(
                dinossauro.getId(),
                dinossauro.getNome(),
                dinossauro.getTamanho(),
                dinossauro.getPeso(),
                dinossauro.getDietaPrincipal(),
                dinossauro.getHabitatNatural(),
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

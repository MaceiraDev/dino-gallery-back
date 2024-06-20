package gallery_dinosaur.DTO;

import gallery_dinosaur.model.*;

public record DinossauroResponseDTO(Long id, String nome,
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
                                    Long subFamilia,
                                    Long user
                                    ) {
    public DinossauroResponseDTO(Dinossauro dinossauro) {
        this(dinossauro.getId(), dinossauro.getNome(),
                dinossauro.getClado().getId(),
                dinossauro.getDieta().getId(),
                dinossauro.getDominio().getId(),
                dinossauro.getEspecie().getId(),
                dinossauro.getFamilia().getId(),
                dinossauro.getFilo().getId(),
                dinossauro.getGenero().getId(),
                dinossauro.getMetodoLocomocao().getId(),
                dinossauro.getPeriodo().getId(),
                dinossauro.getReino().getId(),
                dinossauro.getSubFamilia().getId(),
                dinossauro.getUser().getId());

    }


}

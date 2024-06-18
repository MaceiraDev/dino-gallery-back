package gallery_dinosaur.DTO;

import gallery_dinosaur.model.*;

public record DinossauroResponseDTO(Long id,String nome,
                                    Clado clado,
                                    Dieta dieta,
                                    Dominio dominio,
                                    Especie especie,
                                    Familia familia,
                                    Filo filo,
                                    Genero genero,
                                    MetodoLocomocao metodoLocomocao,
                                    Periodo periodo,
                                    Reino reino,
                                    SubFamilia subFamilia

                                    ) {
    public DinossauroResponseDTO(Dinossauro dinossauro) {
        this(dinossauro.getId(), dinossauro.getNome(),
                dinossauro.getClado(),
                dinossauro.getDieta(),
                dinossauro.getDominio(),
                dinossauro.getEspecie(),
                dinossauro.getFamilia(),
                dinossauro.getFilo(),
                dinossauro.getGenero(),
                dinossauro.getMetodoLocomocao(),
                dinossauro.getPeriodo(),
                dinossauro.getReino(),
                dinossauro.getSubFamilia());
    }
}

package gallery_dinosaur.DTO;

import gallery_dinosaur.model.Filme;

import java.util.Date;

public record FilmeResponseDTO(Long id, String nome, String dtl,
                               String urn,
                               String genero,
                               Date sinopse) {
    public FilmeResponseDTO(Filme filme) {
        this(filme.getId(), filme.getNome(),
                filme.getUrn(),
                filme.getGenero(),
                filme.getSinopse(),
                filme.getDtl());
    }

}

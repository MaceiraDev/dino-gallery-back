package gallery_dinosaur.DTO;


import gallery_dinosaur.model.*;
import jakarta.validation.constraints.NotNull;

public record DinossauroRequestDTO(
        String nome,
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
){

}

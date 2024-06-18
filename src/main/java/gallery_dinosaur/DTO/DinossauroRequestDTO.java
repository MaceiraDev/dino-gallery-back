package gallery_dinosaur.DTO;


import gallery_dinosaur.model.*;
import jakarta.validation.constraints.NotNull;

public record DinossauroRequestDTO(
        @NotNull(message = "O Dinossauro não pode ser nulo")
        String nome,
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

){

}

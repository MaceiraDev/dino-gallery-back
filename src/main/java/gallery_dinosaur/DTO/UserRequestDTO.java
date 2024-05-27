package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
    @NotNull(message ="Crie o Usuario")
    String tipo,
    String email,
    String password,
    String name
){

}

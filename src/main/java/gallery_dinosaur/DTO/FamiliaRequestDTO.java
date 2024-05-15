package gallery_dinosaur.DTO;
import jakarta.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
public record FamiliaRequestDTO(
        @NotNull(message = "O tipo de familia não pode ser nulo")
        @Pattern(regexp = " |ONIVORO", message = "Escolha O tipo de familia deve ser 'ONIVORO'")
        String tipo
){

}

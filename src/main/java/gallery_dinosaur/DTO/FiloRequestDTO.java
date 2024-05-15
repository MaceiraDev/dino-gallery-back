package gallery_dinosaur.DTO;
import jakarta.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
public record FiloRequestDTO(
        @NotNull(message = " Selecione o filo")
        @Pattern(regexp = "CHORDATA|", message = "Escolha o filo 'CHORDATA'")
        String tipo
) {
}

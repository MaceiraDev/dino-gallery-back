package gallery_dinosaur.DTO;
import jakarta.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
public record MetodoLocomocaoRequestDTO(
        @NotNull(message = " Selecione um tipo de locomoção")
        @Pattern(regexp = "AEREO|TERRESTRE|AQUATICO", message = "Escolha o tipo de locomoção 'AEREO', 'TERRESTRE' ou 'AQUATICO'")
        String tipo
) {

}

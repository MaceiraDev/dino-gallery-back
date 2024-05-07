package gallery_dinosaur.DTO;
import jakarta.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
public record PeriodoRequestDTO(
        @NotNull(message = "Escolha um periodo")
        @Pattern(regexp = "CRETACEO|TRIASSICO|JURASSICO", message = "O tipo de periodo deve ser 'CRETACEO', 'TRIASSICO' ou 'JURASSICO'")
        String tipo
) {

}



package gallery_dinosaur.model;

import gallery_dinosaur.DTO.ReinoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "reino")
@Entity(name = "Reinos")
public class Reino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Escolha um Reino")
    @Pattern(regexp = "CRETACEO|TRIASSICO|JURASSICO", message = "O tipo de periodo deve ser 'CRETACEO', 'TRIASSICO' ou 'JURASSICO'")
    private String dataReino;
    private String tipo;

    public Reino(ReinoRequestDTO data) {
        this.tipo = data.tipo();
    }
}


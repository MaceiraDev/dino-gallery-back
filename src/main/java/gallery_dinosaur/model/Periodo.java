package gallery_dinosaur.model;

import gallery_dinosaur.DTO.PeriodoRequestDTO;
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
@Table(name = "periodos")
@Entity(name = "Periodos")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Escolha um periodo")
    @Pattern(regexp = "CRETACEO|TRIASSICO|JURASSICO", message = "O tipo de periodo deve ser 'CRETACEO', 'TRIASSICO' ou 'JURASSICO'")

    private String dataPeriodo;
    private String tipo;

    public Periodo(PeriodoRequestDTO data) {
        this.tipo = data.tipo();
    }
}


package gallery_dinosaur.model;

import gallery_dinosaur.DTO.PeriodoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "periodo")
@Entity(name = "Periodos")
@Getter
@Setter
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Periodo deve ser informado")
    private String dataPeriodo;
    private String tipo;

    public Periodo(PeriodoRequestDTO data) {
        this.tipo = data.tipo();
        this.dataPeriodo = data.dataPeriodo();
    }
}


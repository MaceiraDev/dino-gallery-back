package gallery_dinosaur.model;

import gallery_dinosaur.DTO.PeriodoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "periodo")
@Entity(name = "Periodos")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "Escolha um periodo")
    private String dataPeriodo;
    private String tipo;

    public Periodo(PeriodoRequestDTO data) {
        this.tipo = data.tipo();
        this.dataPeriodo = data.dataPeriodo();
    }

    public void setTipo(String tipo) {
    }
}


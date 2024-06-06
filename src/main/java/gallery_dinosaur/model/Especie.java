package gallery_dinosaur.model;

import gallery_dinosaur.DTO.EspecieRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "especie")
@Entity(name = "Especies")


public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Setter
    @NotNull(message = "Selecione uma Especie")
    String tipo;

    public Especie(EspecieRequestDTO data) {
        this.tipo = data.tipo();
    }


    public void setTipo(String tipo) {
    }
}

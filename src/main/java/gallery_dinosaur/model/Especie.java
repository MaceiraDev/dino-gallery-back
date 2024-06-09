package gallery_dinosaur.model;

import gallery_dinosaur.DTO.EspecieRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private Long id;
    @Setter
    @NotNull(message = "O tipo de especie não pode ser nulo")
    private String tipo;

    public Especie(EspecieRequestDTO data) {
        this.tipo = data.tipo();
    }
}

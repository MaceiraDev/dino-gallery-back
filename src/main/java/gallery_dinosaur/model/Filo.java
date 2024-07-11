package gallery_dinosaur.model;

import gallery_dinosaur.DTO.FiloRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "filo")
@Entity(name = "Filos")
public class Filo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "O tipo de filo não pode ser nulo")
    private String tipo;

    public Filo(FiloRequestDTO data) {
        this.tipo = data.tipo();
    }

}

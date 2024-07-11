package gallery_dinosaur.model;

import gallery_dinosaur.DTO.ReinoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @Setter
    @NotNull(message = "Reino não pode ser nulo")
    private String tipo;

    public Reino(ReinoRequestDTO data) {
        this.tipo = data.tipo();
    }
}


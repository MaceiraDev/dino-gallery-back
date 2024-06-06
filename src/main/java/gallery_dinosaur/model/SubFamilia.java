package gallery_dinosaur.model;


import gallery_dinosaur.DTO.SubFamiliaRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "subfamilia")
@Entity(name = "SubFamilias")
public class SubFamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "Selecione uma SubFamilia")
    private String tipo;

    public SubFamilia(SubFamiliaRequestDTO data) {
        this.tipo = data.tipo();
    }
}

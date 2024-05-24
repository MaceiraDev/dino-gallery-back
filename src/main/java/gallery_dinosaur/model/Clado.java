package gallery_dinosaur.model;


import gallery_dinosaur.DTO.CladoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "clado")
@Entity(name = "Clados")
public class Clado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "O tipo de clado não pode ser nulo")
    @Pattern(regexp = " |DINOSSAURIA", message = "Escolha o tipo de clado  'DINOSSAURIA' ")
    private String tipo;

    public Clado(CladoRequestDTO data) {
        this.tipo = data.tipo();
    }

}




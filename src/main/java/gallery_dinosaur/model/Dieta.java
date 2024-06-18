package gallery_dinosaur.model;

import gallery_dinosaur.DTO.DietaRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "dieta")
@Entity(name = "Dietas")
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "O tipo de dieta não pode ser nulo")
    private String tipo;


    public Dieta(DietaRequestDTO data) {this.tipo = data.tipo();}

}

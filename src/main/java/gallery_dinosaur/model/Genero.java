package gallery_dinosaur.model;


import gallery_dinosaur.DTO.GeneroRequestDTO;
import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Generos")
@Table(name = "genero")
@EqualsAndHashCode(of = "id")

public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "Selecione um genero")
    private String tipo;

    public Genero(GeneroRequestDTO data) {
        this.tipo = data.tipo();
    }

}

package gallery_dinosaur.model;

import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "metodosLocomocao")
@Entity(name = "MetodosLocomocaos")
public class MetodoLocomocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "Metodo de locomocao obrigatorio")
    private String tipo;

    public MetodoLocomocao(MetodoLocomocaoRequestDTO data) {
        this.tipo = data.tipo();
    }

}

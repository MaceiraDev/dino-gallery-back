package gallery_dinosaur.model;

import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "metodosLocomocao")
@Entity(name = "MetodosLocomocao")
public class MetodoLocomocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    @NotNull(message = " Selecione um tipo de locomoção")
    @Pattern(regexp = "AEREO|TERRESTRE|AQUATICO", message = "Escolha o tipo de locomoção 'AEREO', 'TERRESTRE' ou 'AQUATICO'")

    public MetodoLocomocao(MetodoLocomocaoRequestDTO data) {
        this.tipo = data.tipo();
    }

}

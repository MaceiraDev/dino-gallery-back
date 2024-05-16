package gallery_dinosaur.model;

import gallery_dinosaur.DTO.FamiliaRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "familia")
@Entity(name = "Familias")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "Selecione uma Familia")
    @Pattern(regexp = " |CHORDATA", message = "Escolha o tipo de familia 'CHORDATA', ")
    private String tipo;

    public Familia(FamiliaRequestDTO data) {
        this.tipo = data.tipo();
    }
}
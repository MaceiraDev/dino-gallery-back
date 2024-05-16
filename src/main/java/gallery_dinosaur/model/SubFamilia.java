package gallery_dinosaur.model;


import gallery_dinosaur.DTO.SubFamiliaRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Selecione uma SubFamilia")
    @Pattern(regexp = " |CHORDATA", message = "Escolha o tipo de SubFamilia 'CHORDATA', ")
    private String tipo;

    public SubFamilia(SubFamiliaRequestDTO data) {
        this.tipo = data.tipo();
    }
}

package gallery_dinosaur.model;

import gallery_dinosaur.DTO.DominioRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "dominio")
@Entity(name = "Dominios")
public class Dominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @NotNull(message = "Selecione um Dominio")
    private String tipo;

    public Dominio(DominioRequestDTO data) {
        this.tipo = data.tipo();
    }



}



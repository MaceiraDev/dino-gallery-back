package gallery_dinosaur.model;


import gallery_dinosaur.DTO.FilmeRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
@Table(name = "filme")
@Entity(name = "Filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "O nome do filme não pode ser vazio")
    private String nome;
    private String dtl;
    private String urn;
    private String genero;
    private String sinopse;

    public Filme(FilmeRequestDTO data) {
                this.nome = data.nome();
                this.dtl = data.dtl();
                this.urn = data.urn();
                this.genero = data.genero();
                this.sinopse = data.sinopse();
    }
}

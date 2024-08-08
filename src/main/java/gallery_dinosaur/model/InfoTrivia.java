package gallery_dinosaur.model;

import gallery_dinosaur.DTO.InfoTriviaRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "infoTrivia")
@Entity(name = "InfoTrivias")
@Setter
public class InfoTrivia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pergunta;

    @Lob //@Lob é usado para especificar que o campo resposta é um grande objeto de dados.
    @Column(columnDefinition = "TEXT")// é opcional, mas pode ser usado para garantir que o banco de dados trate o campo como um tipo TEXT
    private String resposta;

    public InfoTrivia(InfoTriviaRequestDTO data) {
        this.resposta = data.resposta();
        this.pergunta = data.pergunta();
    }
}

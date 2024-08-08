package gallery_dinosaur.model;

import gallery_dinosaur.DTO.InfoTriviaRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "InfoTrivia")
@Entity(name = "InfoTrivias")
public class InfoTrivia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pergunta;
    private String resposta;

    public InfoTrivia(InfoTriviaRequestDTO data) {
    }
}

package gallery_dinosaur.model;

import gallery_dinosaur.DTO.MezozoicoRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "mezozoico")
@Entity(name = "Mezozoicos")
@Getter


public class Mezozoico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}


package gallery_dinosaur.model;

import gallery_dinosaur.DTO.FiloRequestDTO;
import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "filo")
@Entity(name = "Filos")
public class Filo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    private String tipo;
    @NotNull(message = " Selecione o filo")
    @Pattern(regexp = " |CHORDATA", message = "Escolha o tipo de locomoção 'CHORDATA', ")

            public Filo(FiloRequestDTO data) {
        this.tipo = data.tipo();
    }

}

package gallery_dinosaur.model;

import gallery_dinosaur.DTO.DominioRequestDTO;
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
@Table(name = "dominio")
@Entity(name = "Dominios")
public class Dominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Selecione um Dominio")
    @Pattern(regexp = " |ONIVORO", message = "O tipo de dominio deve ser 'ONIVORO'")
    private String tipo;

    public Dominio(DominioRequestDTO data) {
        this.tipo = data.tipo();
    }
}



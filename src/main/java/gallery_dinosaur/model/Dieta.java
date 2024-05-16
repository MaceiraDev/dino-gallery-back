package gallery_dinosaur.model;

import gallery_dinosaur.DTO.DietaRequestDTO;
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
@Table(name = "dieta")
@Entity(name = "Dietas")
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O tipo de dieta não pode ser nulo")
    private String tipo;
    @Pattern(regexp = "HERBIVORO|CARNIVORO|ONIVORO", message = "O tipo de dieta deve ser 'HERBIVORO', 'CARNIVORO' ou 'ONIVORO'")


    public Dieta(DietaRequestDTO data) {
        this.tipo = data.tipo();
    }
}

package gallery_dinosaur.model;


import gallery_dinosaur.DTO.GeneroRequestDTO;
import gallery_dinosaur.DTO.MetodoLocomocaoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Generos")
@Table(name = "genero")
@EqualsAndHashCode(of = "id")

public class Genero {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long id;
        @NotNull(message = "Selecione uma Familia")
        @Pattern(regexp = " FEM|MASC", message = "Escolha o genero 'FEM', 'MASC' ")
        String tipo;

    public Genero(GeneroRequestDTO data) {
        this.tipo = data.tipo();
    }

}

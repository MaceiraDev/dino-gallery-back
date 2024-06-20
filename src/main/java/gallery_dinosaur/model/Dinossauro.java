package gallery_dinosaur.model;

import gallery_dinosaur.DTO.DinossauroRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "dinossauro")
@Entity(name = "Dinossauros")
public class Dinossauro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String nome;

    @ManyToOne
    @JoinColumn(name = "clado_id")
    private Clado clado;

    @ManyToOne
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

    @ManyToOne
    @JoinColumn(name = "dominio_id")
    private Dominio dominio;

    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;

    @ManyToOne
    @JoinColumn(name = "filo_id")
    private Filo filo;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "metodo_locomocao_id")
    private MetodoLocomocao metodoLocomocao;

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "reino_id")
    private Reino reino;

    @ManyToOne
    @JoinColumn(name = "sub_familia_id")
    private SubFamilia subFamilia;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Dinossauro(DinossauroRequestDTO data, Clado clado, Dieta dieta, Dominio dominio, Especie especie,
                      Familia familia, Filo filo, Genero genero, MetodoLocomocao metodoLocomocao,
                      Periodo periodo, Reino reino, SubFamilia subFamilia, User user) {
        this.nome = data.nome();
        this.clado = clado;
        this.dieta = dieta;
        this.dominio = dominio;
        this.especie = especie;
        this.familia = familia;
        this.filo = filo;
        this.genero = genero;
        this.metodoLocomocao = metodoLocomocao;
        this.periodo = periodo;
        this.reino = reino;
        this.subFamilia = subFamilia;
        this.user = user;
    }
}

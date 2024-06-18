package gallery_dinosaur.model;


import gallery_dinosaur.DTO.DinossauroResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    private String nome;
    @ManyToOne
    private Clado clado;
    @ManyToOne
    private Dieta dieta;
    @ManyToOne
    private Dominio dominio;
    @ManyToOne
    private Especie especie;
    @ManyToOne
    private Familia familia;
    @ManyToOne
    private Filo filo;
    @ManyToOne
    private Genero genero;
    @ManyToOne
    private MetodoLocomocao metodoLocomocao;
    @ManyToOne
    private Periodo periodo;
    @ManyToOne
    private Reino reino;
    @ManyToOne
    private SubFamilia subFamilia;

    public Dinossauro(@Valid DinossauroResponseDTO data) {
        this.nome = data.nome();
        this.clado = data.clado();
        this.dieta = data.dieta();
        this.dominio = data.dominio();
        this.especie = data.especie();
        this.familia = data.familia();
        this.filo = data.filo();
        this.genero = data.genero();
        this.metodoLocomocao = data.metodoLocomocao();
        this.periodo = data.periodo();
        this.reino = data.reino();
        this.subFamilia = data.subFamilia();
    }

}

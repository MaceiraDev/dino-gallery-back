package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

public class DinossauroRequestDTO {
    @NotNull
    private String nome;

    private Float tamanho;

    private Float peso;

    private String dietaPrincipal;

    private String habitatNatural;

    @NotNull
    private String infoCard;

    @NotNull
    private Long cladoId;

    @NotNull
    private Long dietaId;

    @NotNull
    private Long dominioId;

    @NotNull
    private Long especieId;

    @NotNull
    private Long familiaId;

    @NotNull
    private Long filoId;

    @NotNull
    private Long generoId;

    @NotNull
    private Long metodoLocomocaoId;

    @NotNull
    private Long periodoId;

    @NotNull
    private Long reinoId;

    @NotNull
    private Long subFamiliaId;

    public DinossauroRequestDTO() {
    }

    // Getters e setters para cada campo
    public String getNome() {
        return nome;
    }

    public Float getTamanho() {
        return tamanho;
    }

    public Float getPeso() {
        return peso;
    }

    public String getDietaPrincipal() {return dietaPrincipal;}

    public String getHabitatNatural() {
        return habitatNatural;
    }

    public String getInfoCard() { return infoCard;}

    public Long getCladoId() {
        return cladoId;
    }

    public Long getDietaId() {
        return dietaId;
    }

    public Long getDominioId() {
        return dominioId;
    }

    public Long getEspecieId() {
        return especieId;
    }

    public Long getFamiliaId() {
        return familiaId;
    }

    public Long getFiloId() {
        return filoId;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public Long getMetodoLocomocaoId() {
        return metodoLocomocaoId;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public Long getReinoId() {
        return reinoId;
    }

    public Long getSubFamiliaId() {
        return subFamiliaId;
    }
}

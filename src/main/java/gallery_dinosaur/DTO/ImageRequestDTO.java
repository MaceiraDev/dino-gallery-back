package gallery_dinosaur.DTO;

import javax.validation.constraints.NotNull;

public class ImageRequestDTO {

    @NotNull(message = "O link da imagem não pode ser nulo")
    private String url;
    @NotNull(message = "O id do dinossauro não pode ser nulo")
    private Long dinossauroId;

   public String getUrl() { return url; }

   public Long getDinossauroId() { return dinossauroId; }
}

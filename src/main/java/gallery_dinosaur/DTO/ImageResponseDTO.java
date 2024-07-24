package gallery_dinosaur.DTO;

public class ImageResponseDTO {
    private String image;

    public ImageResponseDTO(String image) {
        this.image = image;
    }

    // Getters e setters
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

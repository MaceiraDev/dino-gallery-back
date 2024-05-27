package gallery_dinosaur.DTO;

import gallery_dinosaur.model.User;

public record UserResponseDTO(Long id, String tipo, String name, String password, String email) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getEmail(), user.getTipo(), user.getName(),user.getPassword());
    }
}

package gallery_dinosaur.DTO;

import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

public record DietaRequestDTO(
        @NotNull(message = "O tipo de Dieta não pode ser nulo")
        @Pattern(regexp = "HERBIVORO|CARNIVORO|ONIVORO", message = "O tipo de dieta deve ser 'HERBIVORO', 'CARNIVORO' ou 'ONIVORO'")
        String tipo
) {
}

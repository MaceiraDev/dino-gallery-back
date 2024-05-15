package gallery_dinosaur.repository;

import gallery_dinosaur.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {
}

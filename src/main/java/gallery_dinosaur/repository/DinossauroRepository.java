package gallery_dinosaur.repository;

import gallery_dinosaur.model.Dinossauro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinossauroRepository extends JpaRepository<Dinossauro, Long>  {
}

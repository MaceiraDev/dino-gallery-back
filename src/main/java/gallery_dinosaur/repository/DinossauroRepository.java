package gallery_dinosaur.repository;

import gallery_dinosaur.model.Dinossauro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DinossauroRepository extends JpaRepository<Dinossauro, Long>  {
    List<Dinossauro> findByDieta_Tipo(String tipo);
    Optional<Dinossauro> findByUrn(String urn);
}

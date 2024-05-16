package gallery_dinosaur.repository;

import gallery_dinosaur.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

}

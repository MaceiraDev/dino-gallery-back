package gallery_dinosaur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gallery_dinosaur.model.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
}

package gallery_dinosaur.repository;

import gallery_dinosaur.model.InfoTrivia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoTriviaRepository extends JpaRepository<InfoTrivia, Long> {
}

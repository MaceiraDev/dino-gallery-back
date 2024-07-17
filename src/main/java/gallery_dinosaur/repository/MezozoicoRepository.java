package gallery_dinosaur.repository;

import gallery_dinosaur.model.Mezozoico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MezozoicoRepository extends JpaRepository<Mezozoico, Long> {


    @Query(value = "SELECT COUNT(id) FROM dinossauro", nativeQuery = true)
    int countAllIds();

}

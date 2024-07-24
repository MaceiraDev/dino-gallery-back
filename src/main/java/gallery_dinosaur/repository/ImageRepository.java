package gallery_dinosaur.repository;

import gallery_dinosaur.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByDinossauroId(Long dinossauroId);
    void deleteByDinossauroId(Long dinossauroId);
}

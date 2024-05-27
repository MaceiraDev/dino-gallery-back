package gallery_dinosaur.repository;

import gallery_dinosaur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package cristianmartucci.U5_W2_D4.repositories;

import cristianmartucci.U5_W2_D4.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {
}

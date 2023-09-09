package HDmedi.Server.domain.tag.repository;

import HDmedi.Server.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

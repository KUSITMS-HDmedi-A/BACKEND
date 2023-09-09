package HDmedi.Server.domain.question.repository;

import HDmedi.Server.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

package HDmedi.Server.domain.question.repository;

import HDmedi.Server.domain.question.entity.Question;
import HDmedi.Server.domain.user_child.entity.UserChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question>  findAllByDescriptionIsNotNull();



}

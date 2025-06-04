package falcuty.ntu.groupone.graduation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import falcuty.ntu.groupone.graduation.models.Enrol;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;

public interface IEnrolRepository extends JpaRepository<Enrol, Integer>{
	List<Enrol> findByResearchTopic(ResearchTopic researchTopic);
	Enrol findByStudent(Student student);
	void deleteByResearchTopicAndStateEnrol(ResearchTopic researchTopic, Integer state);
	void deleteByStudentAndStateEnrol(Student student, Integer state);
	Optional<Enrol> findByStudentAndResearchTopic(Student student, ResearchTopic researchTopic);
	int countByResearchTopic(ResearchTopic researchTopic);
}

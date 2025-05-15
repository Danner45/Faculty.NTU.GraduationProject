package falcuty.ntu.groupone.graduation.services.interf;

import java.util.List;
import java.util.Optional;

import falcuty.ntu.groupone.graduation.models.Enrol;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;

public interface IEnrolService {
	List<Enrol> getEnrolListByProject(ResearchTopic researchTopic);
	List<Enrol> getEnrolListByStudent(Student student);
	Optional<Enrol> findErolListByStudentAndProject(ResearchTopic researchTopic, Student student);
	void deleteByResearchTopicAndStateEnrol(ResearchTopic researchTopic, Integer state);
	void deleteByStudentAndStateEnrol(Student student, Integer state);
	int countStudentEnrol(ResearchTopic researchTopic);
	void confirmEnrol(Student student, ResearchTopic researchTopic);
}

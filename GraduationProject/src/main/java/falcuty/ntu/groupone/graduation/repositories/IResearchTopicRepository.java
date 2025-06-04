package falcuty.ntu.groupone.graduation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface IResearchTopicRepository extends JpaRepository<ResearchTopic, Integer>{
	List<ResearchTopic> findByTeacherCreatedAndIsResearchAndCourse(Supervisor supervisor, boolean isResearch, Course course);
	ResearchTopic findResearchTopicByIdResearchTopic(int id);
	@Query("SELECT rt FROM ResearchTopic rt WHERE rt.state = 0")
    List<ResearchTopic> findAllByStateZero();
	@Query("SELECT rt FROM ResearchTopic rt WHERE rt.state = 1")
    List<ResearchTopic> findAllByStateOne();
}

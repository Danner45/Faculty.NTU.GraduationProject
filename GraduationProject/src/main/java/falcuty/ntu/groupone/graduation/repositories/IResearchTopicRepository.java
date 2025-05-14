package falcuty.ntu.groupone.graduation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;

@Repository
public interface IResearchTopicRepository extends JpaRepository<ResearchTopic, Integer>{
	List<ResearchTopic> findByTeacherCreatedId(Integer teacherId); // ví dụ lọc theo giáo viên tạo đề tài
=======

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface IResearchTopicRepository extends JpaRepository<ResearchTopic, Integer>{
	List<ResearchTopic> findByTeacherCreatedAndIsResearchAndCourse(Supervisor supervisor, boolean isResearch, Course course);
	ResearchTopic findResearchTopicByIdResearchTopic(int id);
>>>>>>> main
}

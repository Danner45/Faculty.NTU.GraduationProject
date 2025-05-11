package falcuty.ntu.groupone.graduation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;

@Repository
public interface IResearchTopicRepository extends JpaRepository<ResearchTopic, Integer>{
	List<ResearchTopic> findByTeacherCreatedId(Integer teacherId); // ví dụ lọc theo giáo viên tạo đề tài
}

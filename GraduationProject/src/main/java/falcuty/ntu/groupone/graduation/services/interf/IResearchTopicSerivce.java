package falcuty.ntu.groupone.graduation.services.interf;

import java.util.List;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface IResearchTopicSerivce {
	List<ResearchTopic> findAllResearchOrTopicForCourse(Course idCourse, boolean isResearch);
	List<ResearchTopic> findAllTeacherResearchTopic(Supervisor supervisor, boolean isResearch, Course course);
	ResearchTopic findResearchTopicById(int id);
	ResearchTopic addResearchTopic(ResearchTopic researchTopic);
	ResearchTopic saveResearchTopic(ResearchTopic researchTopic);
	List<ResearchTopic> getTopicsByFilters(String searchTopic, Integer filterCourse, String filterType, Integer filterStatus);

}

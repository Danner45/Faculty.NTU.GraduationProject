package falcuty.ntu.groupone.graduation.services.interf;

import java.util.List;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;

public interface IResearchTopicSerivce {
	List<ResearchTopic> findAllResearchOrTopicForCourse(int idCourse, boolean isResearch);
	List<ResearchTopic> findAllTeacherResearchTopic(int idSupervisor, boolean isResearch);
	ResearchTopic addResearchTopic(int id, ResearchTopic researchTopic);
}

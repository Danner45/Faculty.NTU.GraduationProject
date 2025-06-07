package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.repositories.IResearchTopicRepository;
import falcuty.ntu.groupone.graduation.services.interf.IResearchTopicSerivce;

@Service
public class ResearchTopicService implements IResearchTopicSerivce{
	
	@Autowired
	IResearchTopicRepository researchTopicRepository;
	
	public List<ResearchTopic> findAllByStateZero() {
        return researchTopicRepository.findAllByStateZero();
    }
	public List<ResearchTopic> findAllByStateOne() {
        return researchTopicRepository.findAllByStateOne();
    }
	@Override
	public List<ResearchTopic> findAllResearchOrTopicForCourse(Course course, boolean isResearch) {
		return null;
	}

	@Override
	public List<ResearchTopic> findAllTeacherResearchTopic(Supervisor supervisor, boolean isResearch, Course course) {
		return researchTopicRepository.findByTeacherCreatedAndIsResearchAndCourse(supervisor, isResearch, course);
	}

	@Override
	public ResearchTopic addResearchTopic(ResearchTopic researchTopic) {
		return researchTopicRepository.save(researchTopic);
	}

	@Override
	public ResearchTopic findResearchTopicById(int id) {
		return researchTopicRepository.findResearchTopicByIdResearchTopic(id);
	}
	
	@Override
	public ResearchTopic saveResearchTopic(ResearchTopic researchTopic) {
		return researchTopicRepository.save(researchTopic);
	}
	

	
}

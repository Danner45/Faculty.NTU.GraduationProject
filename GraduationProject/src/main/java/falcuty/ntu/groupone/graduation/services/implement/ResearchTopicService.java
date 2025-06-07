package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.repositories.IResearchTopicRepository;
import falcuty.ntu.groupone.graduation.repositories.ISupervisorRepository;
import falcuty.ntu.groupone.graduation.services.interf.IResearchTopicSerivce;

@Service
public class ResearchTopicService implements IResearchTopicSerivce{
	
	@Autowired
	IResearchTopicRepository researchTopicRepository;
	@Autowired
    private ISupervisorRepository supervisorRepository;

	public List<ResearchTopic> findAllByStateOne() {
        return researchTopicRepository.findAllByStateOne();
    }
	public List<ResearchTopic> getTopicsByTeacherId(Integer teacherId) {
        return researchTopicRepository.findByTeacherCreatedId(teacherId);
    }
	
	public Supervisor findSupervisorByEmail(String email) {
        return supervisorRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy giáo viên với email: " + email));
    }
	
	public List<ResearchTopic> findAllByStateZero() {
        return researchTopicRepository.findAllByStateZero();
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

	public List<ResearchTopic> getTopicsByTeacherIdAndFilters(
	        Integer teacherId, String searchTopic, Integer filterCourse, String filterType, Integer filterStatus) {

		List<ResearchTopic> topics = researchTopicRepository.findByTeacherCreatedId(teacherId);

	        return topics.stream()
	            .filter(t -> searchTopic == null || searchTopic.isEmpty() || t.getTopic().toLowerCase().contains(searchTopic.toLowerCase()))
	            .filter(t -> filterCourse == null || (t.getCourse() != null && t.getCourse().getIdCourse().equals(filterCourse)))
	            .filter(t -> filterType == null || filterType.isEmpty() || (t.getProjectType() != null && t.getProjectType().getName().equals(filterType)))
	            .filter(t -> filterStatus == null || t.getState() == filterStatus)
	            .collect(Collectors.toList());
	    }
	
	@Override
    public List<ResearchTopic> getTopicsByFilters(String searchTopic, Integer filterCourse, String filterType, Integer filterStatus) {
        List<ResearchTopic> allTopics = researchTopicRepository.findAll();

        return allTopics.stream()
        		.filter(t -> t.getState() != 0)
                .filter(t -> searchTopic == null || searchTopic.isEmpty() || t.getTopic().toLowerCase().contains(searchTopic.toLowerCase()))
                .filter(t -> filterCourse == null || (t.getCourse() != null && t.getCourse().getIdCourse().equals(filterCourse)))
                .filter(t -> filterType == null || filterType.isEmpty() || (t.getProjectType() != null && t.getProjectType().getName().equals(filterType)))
                .filter(t -> filterStatus == null || t.getState() == filterStatus)
                .collect(Collectors.toList());
    }
	
}

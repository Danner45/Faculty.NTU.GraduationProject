package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Enrol;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.repositories.IEnrolRepository;
import falcuty.ntu.groupone.graduation.services.interf.IEnrolService;
import jakarta.transaction.Transactional;

@Service
public class EnrolService implements IEnrolService{
	
	@Autowired
	private IEnrolRepository enrolRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ResearchTopicService researchTopicService;

	@Override
	public List<Enrol> getEnrolListByProject(ResearchTopic researchTopic) {
		return enrolRepository.findByResearchTopic(researchTopic);
	}

	@Override
	public Enrol getEnrolListByStudent(Student student) {
		return enrolRepository.findByStudent(student);
	}

	@Override
	public Optional<Enrol> findErolListByStudentAndProject(ResearchTopic researchTopic, Student student) {
		return enrolRepository.findByStudentAndResearchTopic(student, researchTopic);
	}

	@Override
	public void deleteByResearchTopicAndStateEnrol(ResearchTopic researchTopic, Integer state) {
		enrolRepository.deleteByResearchTopicAndStateEnrol(researchTopic, state);
		
	}

	@Override
	public void deleteByStudentAndStateEnrol(Student student, Integer state) {
		enrolRepository.deleteByStudentAndStateEnrol(student, state);
	}

	@Override
	public int countStudentEnrol(ResearchTopic researchTopic) {
		return enrolRepository.countByResearchTopic(researchTopic);
	}

	@Override
	@Transactional
	public void confirmEnrol(String studentId, Integer researchTopicId) {
		Student student = studentService.findStudentById(studentId).get();
	    ResearchTopic topic = researchTopicService.findResearchTopicById(researchTopicId);

	    Enrol enrol = enrolRepository.findByStudentAndResearchTopic(student, topic)
	        .orElseThrow(() -> new RuntimeException("Enrol not found"));

	    enrol.setStateEnrol(1);
	    topic.setState(2);
	    researchTopicService.saveResearchTopic(topic);
	    
	    enrolRepository.save(enrol);

	    enrolRepository.deleteByResearchTopicAndStateEnrol(topic, 0);

	    enrolRepository.deleteByStudentAndStateEnrol(student, 0);
		
	}
	
	public void saveEnrol(Enrol enrol) {
		enrolRepository.save(enrol);
	}
	
	public void deleteById(Integer id) {
		enrolRepository.deleteById(id);
	}
	
	public Enrol findByStateAndProject(int state, ResearchTopic researchTopic) {
		return enrolRepository.findByStateEnrolAndResearchTopic(state, researchTopic);
	}
	
	@Override
    public boolean existsOtherEnrolByStudent(String studentId, Integer currentTopicId) {
        List<Enrol> enrols = enrolRepository.findByStudent_Id(studentId);
        for (Enrol e : enrols) {
            ResearchTopic topic = e.getResearchTopic();
            if (topic != null && topic.getIdResearchTopic() != null
                    && !topic.getIdResearchTopic().equals(currentTopicId)) {
                return true;
            }
        }
        return false;
    }
	
}

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
	public List<Enrol> getEnrolListByStudent(Student student) {
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
	public void confirmEnrol(String studentId, Integer researchTopicId) {
		Student student = studentService.findStudentById(studentId).get();
	    ResearchTopic topic = researchTopicService.findResearchTopicById(researchTopicId);

	    Enrol enrol = enrolRepository.findByStudentAndResearchTopic(student, topic)
	        .orElseThrow(() -> new RuntimeException("Enrol not found"));

	    enrol.setStateEnrol(1);
	    topic.setState(1);
	    enrolRepository.save(enrol);

	    enrolRepository.deleteByResearchTopicAndStateEnrol(topic, 0);

	    enrolRepository.deleteByStudentAndStateEnrol(student, 0);
		
	}
	
	
	
}

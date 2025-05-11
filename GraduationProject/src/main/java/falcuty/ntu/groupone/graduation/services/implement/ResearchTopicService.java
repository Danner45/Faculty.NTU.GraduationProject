package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 

import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.repositories.IResearchTopicRepository;
import falcuty.ntu.groupone.graduation.repositories.ISupervisorRepository;

@Service
public class ResearchTopicService {
    @Autowired
    private IResearchTopicRepository researchTopicRepository;
    
    @Autowired
    private ISupervisorRepository supervisorRepository;


    public List<ResearchTopic> getTopicsByTeacherId(Integer teacherId) {
        return researchTopicRepository.findByTeacherCreatedId(teacherId);
    }
    
    public Supervisor findSupervisorByEmail(String email) {
        return supervisorRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy giáo viên với email: " + email));
    }
}

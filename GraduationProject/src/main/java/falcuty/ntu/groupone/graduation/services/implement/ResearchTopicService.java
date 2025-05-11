package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.repositories.IResearchTopicRepository;

@Service
public class ResearchTopicService {
    @Autowired
    private IResearchTopicRepository researchTopicRepository;

    public List<ResearchTopic> getTopicsByTeacherId(Integer teacherId) {
        return researchTopicRepository.findByTeacherCreatedId(teacherId);
    }
}

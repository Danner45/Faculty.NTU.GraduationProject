package falcuty.ntu.groupone.graduation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.ProjectType;
import falcuty.ntu.groupone.graduation.repositories.IProjectTypeRepository;

@Service
public class ProjectTypeService {
	@Autowired
    private IProjectTypeRepository projectTypeRepository;
	
	public List<ProjectType> getAllProjectTypes() {
        return projectTypeRepository.findAll();
    }
}

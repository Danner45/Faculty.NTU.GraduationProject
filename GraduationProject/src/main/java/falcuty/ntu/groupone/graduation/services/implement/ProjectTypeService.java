package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<ProjectType> findProjectTypeById(int id) {
		return projectTypeRepository.findById(id);
	}
	
	public void save(ProjectType projectType) {
		projectTypeRepository.save(projectType);
	}
}

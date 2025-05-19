package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.repositories.ISupervisorRepository;
import falcuty.ntu.groupone.graduation.services.interf.ISupervisorService;

@Service
public class SupervisorService implements ISupervisorService{

	@Autowired
	private ISupervisorRepository supervisorRepository;
	
	public SupervisorService(ISupervisorRepository supervisorRespository) {
		this.supervisorRepository = supervisorRespository;
	}

	@Override
	public List<Supervisor> getAllSupervisors() {
		return supervisorRepository.findAll();
	}

	@Override
    public Supervisor saveSupervisor(String email, Supervisor updatedSupervisor) {
        return supervisorRepository.findByEmail(email)
            .map(existing -> {
                existing.setCv(updatedSupervisor.getCv());
                existing.setImgUrl(updatedSupervisor.getImgUrl());
                return supervisorRepository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Supervisor not found with ID: " + email));
    }

	@Override
	public Optional<Supervisor> findSupervisorById(Integer id) {
		return supervisorRepository.findById(id);
	}

	@Override
	public Optional<Supervisor> findSupervisorByEmail(String email) {
		return supervisorRepository.findByEmail(email);
	}

	
}

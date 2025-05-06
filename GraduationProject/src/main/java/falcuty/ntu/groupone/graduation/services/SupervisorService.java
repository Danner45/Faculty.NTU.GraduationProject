package falcuty.ntu.groupone.graduation.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.respositories.ISupervisorRespository;

@Service
public class SupervisorService implements ISupervisorService{

	@Autowired
	private ISupervisorRespository supervisorRespository;
	
	public SupervisorService(ISupervisorRespository supervisorRespository) {
		this.supervisorRespository = supervisorRespository;
	}

	@Override
	public List<Supervisor> getAllSupervisors() {
		return supervisorRespository.findAll();
	}

	@Override
    public Supervisor saveSupervisor(Integer id, Supervisor updatedSupervisor) {
        return supervisorRespository.findById(id)
            .map(existing -> {
                existing.setCv(updatedSupervisor.getCv());
                existing.setImgUrl(updatedSupervisor.getImgUrl());
                return supervisorRespository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Supervisor not found with ID: " + id));
    }

	@Override
	public Optional<Supervisor> findSupervisorById(Integer id) {
		return supervisorRespository.findById(id);
	}

	
}

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
	public Supervisor saveSupervisor(Supervisor supervisor) {
		
		return null;
	}

	@Override
	public Optional<Supervisor> findSupervisorById(int id) {
		return supervisorRespository.findById(id);
	}

	
}

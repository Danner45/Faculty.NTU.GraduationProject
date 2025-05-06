package falcuty.ntu.groupone.graduation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.respositories.ISupervisorRespository;

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
	public Supervisor findSupervisorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

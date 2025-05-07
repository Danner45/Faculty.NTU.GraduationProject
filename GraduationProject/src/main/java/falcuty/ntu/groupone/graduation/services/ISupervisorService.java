package falcuty.ntu.groupone.graduation.services;

import java.util.List;
import java.util.Optional;

import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface ISupervisorService {
	public List<Supervisor> getAllSupervisors();
	public Supervisor saveSupervisor(Integer id,Supervisor supervisor);
	public Optional<Supervisor> findSupervisorById(Integer id);
}

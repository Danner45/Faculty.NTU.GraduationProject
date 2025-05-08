package falcuty.ntu.groupone.graduation.services;

import java.util.List;
import java.util.Optional;

import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface ISupervisorService {
	List<Supervisor> getAllSupervisors();
	Supervisor saveSupervisor(Integer id,Supervisor supervisor);
	Optional<Supervisor> findSupervisorById(Integer id);
	Optional<Supervisor> findSupervisorByEmail(String email);
}

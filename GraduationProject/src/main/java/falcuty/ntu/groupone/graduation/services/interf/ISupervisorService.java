package falcuty.ntu.groupone.graduation.services.interf;

import java.util.List;
import java.util.Optional;

import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface ISupervisorService {
	List<Supervisor> getAllSupervisors();
	Supervisor saveSupervisor(String email,Supervisor supervisor);
	Optional<Supervisor> findSupervisorById(Integer id);
	Optional<Supervisor> findSupervisorByEmail(String email);
}

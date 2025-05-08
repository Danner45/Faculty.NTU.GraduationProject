package falcuty.ntu.groupone.graduation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import falcuty.ntu.groupone.graduation.models.Supervisor;

public interface ISupervisorRepository extends JpaRepository<Supervisor, Integer>{
	Optional<Supervisor> findByEmail(String email);
}

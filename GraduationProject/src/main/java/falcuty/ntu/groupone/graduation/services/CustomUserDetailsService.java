package falcuty.ntu.groupone.graduation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import falcuty.ntu.groupone.graduation.repositories.IStudentRepository;
import falcuty.ntu.groupone.graduation.repositories.ISupervisorRepository;

public class CustomUserDetailsService implements UserDetailsService{
	private final IStudentRepository studentRepository;
    private final ISupervisorRepository supervisorRepository;

    @Autowired
    public CustomUserDetailsService(IStudentRepository studentRepository, ISupervisorRepository supervisorRepository) {
        this.studentRepository = studentRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findByEmail(username)
                .<UserDetails>map(student -> new CustomUserDetails(
                        student.getEmail(),
                        student.getPassword(),
                        "ROLE_STUDENT"))
                .or(() -> supervisorRepository.findByEmail(username)
                        .map(supervisor -> new CustomUserDetails(
                                supervisor.getEmail(),
                                supervisor.getPassword(),
                                "ROLE_SUPERVISOR")))
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với email: " + username));
    }

}

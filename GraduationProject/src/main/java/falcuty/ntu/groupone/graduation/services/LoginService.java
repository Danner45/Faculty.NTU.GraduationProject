package falcuty.ntu.groupone.graduation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.repositories.IStudentRepository;
import falcuty.ntu.groupone.graduation.repositories.ISupervisorRepository;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private SupervisorService supervisorRepository;

    @Autowired
    private StudentService studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Supervisor> optionalSup = supervisorRepository.findSupervisorByEmail(email);
        if (optionalSup.isPresent()) {
            Supervisor s = optionalSup.get();
            return User.builder()
                    .username(s.getEmail())
                    .password("{noop}" + s.getPassword()) 
                    .roles("SUPERVISOR")
                    .build();
        }

        Optional<Student> optionalStu = studentRepository.findStudentByEmail(email);
        if (optionalStu.isPresent()) {
            Student s = optionalStu.get();
            return User.builder()
                    .username(s.getEmail())
                    .password(s.getPassword())
                    .roles("STUDENT")
                    .build();
        }

        throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
    }
}
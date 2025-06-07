package falcuty.ntu.groupone.graduation.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordMigrationService {
//
//    @Autowired
//    private SupervisorRepository supervisorRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void encodeAllPasswords() {
//        // Mã hóa mật khẩu của Supervisor
//        supervisorRepository.findAll().forEach(sup -> {
//            String rawPassword = sup.getPassword();
//            if (!rawPassword.startsWith("$2a$")) { // Kiểm tra xem đã mã hóa chưa
//                sup.setPassword(passwordEncoder.encode(rawPassword));
//                supervisorRepository.save(sup);
//            }
//        });
//
//        // Mã hóa mật khẩu của Student
//        studentRepository.findAll().forEach(stu -> {
//            String rawPassword = stu.getPassword();
//            if (!rawPassword.startsWith("$2a$")) { // Kiểm tra xem đã mã hóa chưa
//                stu.setPassword(passwordEncoder.encode(rawPassword));
//                studentRepository.save(stu);
//            }
//        });
//
//        System.out.println("✅ Mã hóa mật khẩu hoàn tất.");
//    }
}


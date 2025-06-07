package falcuty.ntu.groupone.graduation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GraduationProjectApplication {
//	@Autowired
//    private PasswordMigrationService passwordMigrationService;

	public static void main(String[] args) {
		SpringApplication.run(GraduationProjectApplication.class, args);
	}
	
//	@Bean
//    public CommandLineRunner run() {
//        return args -> {
//            passwordMigrationService.encodeAllPasswords();
//        };
//    }
}

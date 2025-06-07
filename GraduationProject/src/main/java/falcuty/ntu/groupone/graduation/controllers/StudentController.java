package falcuty.ntu.groupone.graduation.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import falcuty.ntu.groupone.graduation.models.CountResearchTopic;
import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.Enrol;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.EnrolService;
import falcuty.ntu.groupone.graduation.services.implement.ProjectTypeService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.StudentService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@Autowired
	private EnrolService enrolService;
	
	@Autowired
	private ServletContext servletContext;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/home")
	public String getHome(HttpServletRequest request, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
	    String email = userDetails.getUsername();
	    Optional<Student> studentOpt = studentService.findStudentByEmail(email);
	    
	    if (studentOpt.isPresent()) {
	        Student student = studentOpt.get();
	        model.addAttribute("type", "student");
	        model.addAttribute("name", student.getName());
	        model.addAttribute("email", student.getEmail());
	        Enrol enrol = enrolService.getEnrolListByStudent(student);
	        if(enrol != null) {
	        	int count = enrolService.countStudentEnrol(enrol.getResearchTopic());
			    model.addAttribute("count", count);
	        	model.addAttribute("enrol", enrol);
	        }
	        else {
	        	List<ResearchTopic> researchTopics = researchTopicService.findAllByStateOne(); 
		        model.addAttribute("researchtopics", researchTopics);
			}
	    } else {
	        model.addAttribute("name", "Người dùng không xác định");
	    }

	    model.addAttribute("currentPath", request.getRequestURI());
	    return "student/index";
	}

	@GetMapping("/project/detail/{id}")
	public String getDetailProject(@PathVariable Integer id,
									@AuthenticationPrincipal UserDetails userDetails,
			 						ModelMap model) {
		String email = userDetails.getUsername();
		Optional<Student> student = studentService.findStudentByEmail(email);
		ResearchTopic researchTopic = researchTopicService.findResearchTopicById(id);
		Enrol enrol = enrolService.getEnrolListByStudent(student.get());
        if(enrol != null) {
        	model.addAttribute("enrol",enrol);
        }
		if(researchTopic.getState() == 1) {
			int countStudent = enrolService.countStudentEnrol(researchTopic);
	        model.addAttribute("count", countStudent);
		}
		else if(researchTopic.getState() == 2) {
			//model.addAttribute("isBeforeDeadline", !researchTopic.getExpireDay().toLocalDate().isBefore(LocalDate.now())); 
		}
		model.addAttribute("type", "student");
		model.addAttribute("email", email);
        model.addAttribute("name", student.get().getName());
        model.addAttribute("researchtopic", researchTopic);
        return "student/project_detail";
	}
	
	@PostMapping("/enrol/{id}")
	public String getEnrol(@PathVariable Integer id,
			@AuthenticationPrincipal UserDetails userDetails,
				ModelMap model) {
		String email = userDetails.getUsername();
		Optional<Student> student = studentService.findStudentByEmail(email);
		ResearchTopic researchTopic = researchTopicService.findResearchTopicById(id);
		Enrol enrol = new Enrol();
		enrol.setStateEnrol(0);
		enrol.setStudent(student.get());
		enrol.setResearchTopic(researchTopic);
		enrolService.saveEnrol(enrol);
		return "redirect:/students/home";
	}
	
	@PostMapping("/cancel_enrol/{id}")
	public String HandleCancelEnrol(@PathVariable Integer id,
			@AuthenticationPrincipal UserDetails userDetails,
			RedirectAttributes redirectAttributes) {
		String email = userDetails.getUsername();
		Optional<Student> student = studentService.findStudentByEmail(email);
		enrolService.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "Hủy đăng ký thành công!");
		return "redirect:/students/home";
	}
	
	@PostMapping("/project/upload_report")
	public String HandleSaveFileReport(@AuthenticationPrincipal UserDetails userDetails,
										@RequestParam("reportFile") MultipartFile file) {
		String email = userDetails.getUsername();
		Optional<Student> student = studentService.findStudentByEmail(email);
		Enrol enrol = enrolService.getEnrolListByStudent(student.get());
		Integer idProject = enrol.getResearchTopic().getIdResearchTopic();
		if (!file.isEmpty()) {
		    try {
		        // Lấy tên gốc của file và phần mở rộng
		        String originalFilename = file.getOriginalFilename();
		        String extension = "";

		        if (originalFilename != null && originalFilename.contains(".")) {
		            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		        }

		        // Tạo tên file mới
		        String filename = student.get().getName().replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + extension;

		        // Thư mục lưu file
		        String uploadPath = servletContext.getRealPath("/uploads");
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdirs();
		        }

		        // Lưu file
		        File savedFile = new File(uploadPath, filename);
		        file.transferTo(savedFile);

		        // Lưu đường dẫn hoặc tên file
		        enrol.setReport(filename);

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		enrolService.saveEnrol(enrol);
		return "redirect:/students/project/detail/" + idProject;
	}
}

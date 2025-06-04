package falcuty.ntu.groupone.graduation.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.model.Model;
import falcuty.ntu.groupone.graduation.models.CountResearchTopic;
import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.Enrol;
import falcuty.ntu.groupone.graduation.models.ProjectType;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.EnrolService;
import falcuty.ntu.groupone.graduation.services.implement.ProjectTypeService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.StudentService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/supervisors")
public class SupervisorController {
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@Autowired
	private EnrolService enrolService;
	
	@Autowired
	private StudentService studentService;
	
	
	public SupervisorController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	
	@GetMapping("/home")
	public String getHome(HttpServletRequest request, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
		if (supervisorOpt.isPresent()) {
			int currentYear = LocalDate.now().getYear();
			Course course = courseService.findCourseByGraduationYear(currentYear);
			List<ResearchTopic> researchTopics = researchTopicService.findAllTeacherResearchTopic(supervisorOpt.get(), true, course);
			researchTopics.addAll(researchTopicService.findAllTeacherResearchTopic(supervisorOpt.get(), false, course));
			List<CountResearchTopic> countResearchTopics = new ArrayList<>();

			for (ResearchTopic topic : researchTopics) {
			    int count = enrolService.countStudentEnrol(topic);
			    countResearchTopics.add(new CountResearchTopic(topic, count));
			}
			System.out.println(countResearchTopics.size());
			model.addAttribute("name", supervisorOpt.get().getName());
			model.addAttribute("counts", countResearchTopics);
			model.addAttribute("course", course);
		} else {
		    model.addAttribute("name", "Người dùng không xác định");
		}
		model.addAttribute("email", email);
		model.addAttribute("currentPath", request.getRequestURI());
		return "supervisor/index";
	}
	
	@GetMapping("/project/create")
    public String newProject(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
        String email = userDetails.getUsername();

        Supervisor supervisor = supervisorService.findSupervisorByEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với email: " + email));
        List<ProjectType> projectTypes = projectTypeService.getAllProjectTypes();
        int currentYear = LocalDate.now().getYear();
        Course course = courseService.findCourseByGraduationYear(currentYear);
        model.addAttribute("type", "supervisor");
        model.addAttribute("project", new ResearchTopic());
        model.addAttribute("email", email);
        model.addAttribute("name", supervisor.getName());
        model.addAttribute("projectTypes", projectTypes);
        model.addAttribute("course", course);
        return "supervisor/project_new";
    }
	
	@PostMapping("/project/add")
	public String handleCreateProject(@ModelAttribute ResearchTopic project,
	                                  @RequestParam("detailFile") MultipartFile file,
	                                  @AuthenticationPrincipal UserDetails userDetails) {

	    Supervisor supervisor = supervisorService.findSupervisorByEmail(userDetails.getUsername())
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên"));

	    Course course = courseService.findCourseByGraduationYear(LocalDate.now().getYear());

	    if (!file.isEmpty()) {
	        String uploadDir = "D:/upload-folder/";

	        // Tạo thư mục nếu chưa có
	        File uploadPath = new File(uploadDir);
	        if (!uploadPath.exists()) {
	            uploadPath.mkdirs();
	        }

	        // Tạo fileName tùy chỉnh, ví dụ: uuid + gốc tên file
	        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
	        String fileExtension = "";

	        int dotIndex = originalFileName.lastIndexOf('.');
	        if (dotIndex > 0) {
	            fileExtension = originalFileName.substring(dotIndex);
	        }

	        String fileName = project.getTopic().replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + ".pdf";

	        // Dùng Path.resolve() thay vì + để tránh lỗi đường dẫn
	        Path path = Paths.get(uploadDir).resolve(fileName);

	        try {
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	            // Lưu đường dẫn tương đối để hiển thị
	            project.setDetail("/files/" + fileName);

	        } catch (IOException e) {
	            e.printStackTrace();
	            // Có thể thêm xử lý lỗi hoặc báo lỗi cho người dùng
	        }
	    } else {
	        System.out.println("Không nhận được file");
	    }

	    project.setCourse(course);
	    project.setTeacherCreated(supervisor);
	    project.setState(0);
	    project.setMaxJoin(1);

	    researchTopicService.addResearchTopic(project);

	    return "redirect:/supervisors/home";
	}
	
	@GetMapping("/project/detail/{id}")
	public String getDetailProject(@PathVariable Integer id,
									@AuthenticationPrincipal UserDetails userDetails,
			 						ModelMap model) {
		String email = userDetails.getUsername();
		Supervisor supervisor = supervisorService.findSupervisorByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với email: " + email));
		ResearchTopic researchTopic = researchTopicService.findResearchTopicById(id);
		int countStudent = enrolService.countStudentEnrol(researchTopic);
		model.addAttribute("type", "supervisor");
		model.addAttribute("email", email);
        model.addAttribute("name", supervisor.getName());
        model.addAttribute("researchtopic", researchTopic);
        model.addAttribute("count", countStudent);
        return "supervisor/project_detail";
	}
	
	@GetMapping("/project/detail/{id}/enrol_list")
	public String getEnrolList(@PathVariable Integer id,
								@AuthenticationPrincipal UserDetails userDetails,
								ModelMap model) {
		ResearchTopic researchTopic = researchTopicService.findResearchTopicById(id);
		List<Enrol> enrols = enrolService.getEnrolListByProject(researchTopic);
		String email = userDetails.getUsername();
		Supervisor supervisor = supervisorService.findSupervisorByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với email: " + email));
		int count = enrols.size();
		model.addAttribute("topic", researchTopic.getTopic());
		model.addAttribute("email", email);
        model.addAttribute("name", supervisor.getName());
		model.addAttribute("enrols", enrols);
		model.addAttribute("count", count);
		return "supervisor/project_enrol_list";
	}
	
	@GetMapping("/project/enrol/confirm")
    public String confirmEnrol(@RequestParam("studentId") String studentId,
                               @RequestParam("projectId") Integer topicId) {
		enrolService.confirmEnrol(studentId, topicId);
        return "redirect:/supervisors/project/detail/" + topicId;
    }

	@GetMapping("/project/edit/{id}")
	public String showEditProject(@PathVariable Integer id,
	                              @AuthenticationPrincipal UserDetails userDetails,
	                              ModelMap model) {
	    ResearchTopic topic = researchTopicService.findResearchTopicById(id);

	    List<ProjectType> projectTypes = projectTypeService.getAllProjectTypes();
	    int currentYear = LocalDate.now().getYear();
        Course course = courseService.findCourseByGraduationYear(currentYear);
	    model.addAttribute("project", topic);
	    model.addAttribute("projectTypes", projectTypes);
	    model.addAttribute("course", course);
	    return "supervisor/project_edit"; // trỏ đến file HTML form chỉnh sửa
	}
	
	@PostMapping("/project/edit/{id}")
	public String handleEditProject(@PathVariable Integer id,
	                                @ModelAttribute ResearchTopic project,
	                                @RequestParam("detailFile") MultipartFile file,
	                                @AuthenticationPrincipal UserDetails userDetails) {

	    ResearchTopic oldProject = researchTopicService.findResearchTopicById(id);
	    if (oldProject == null) {
	        throw new RuntimeException("Không tìm thấy đề tài");
	    }

	    // Giữ lại các trường không sửa từ form
	    project.setIdResearchTopic(id); // đảm bảo update đúng ID
	    project.setCourse(oldProject.getCourse());
	    project.setTeacherCreated(oldProject.getTeacherCreated());
	    project.setState(oldProject.getState());
	    project.setMaxJoin(oldProject.getMaxJoin());

	    // Xử lý file
	    if (!file.isEmpty()) {
	        String uploadDir = "D:/upload-folder/";
	        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
	        
	        // Tùy chọn: đổi tên file tránh trùng
	        String fileName = project.getTopic().replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + ".pdf";
	        
	        Path path = Paths.get(uploadDir + fileName);
	        try {
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	            project.setDetail("/files/" + fileName);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        // Nếu không upload file mới, giữ nguyên đường dẫn file cũ
	        project.setDetail(oldProject.getDetail());
	    }

	    researchTopicService.saveResearchTopic(project);
	    return "redirect:/supervisors/home";
	}

	
	@GetMapping("/project/all")
	public String getMethodName(ModelMap model, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
		String email = userDetails.getUsername();
		Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
		model.addAttribute("name", supervisorOpt.get().getName());
		model.addAttribute("email", email);
		model.addAttribute("currentPath", request.getRequestURI());
		return "supervisor/project_list";
	}
	
}

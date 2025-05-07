package falcuty.ntu.groupone.graduation.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.SupervisorService;

@Controller
@RequestMapping("/supervisors")
public class SupervisorController {
	@Autowired
	private SupervisorService supervisorService;
	
	public SupervisorController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	@GetMapping("/detail/{id}")
	public String getDetail(@PathVariable Integer id,ModelMap model) {
		Supervisor supervisor = supervisorService.findSupervisorById(id).orElseThrow(() -> new RuntimeException("Supervisor not found with ID: " + id));;
		model.addAttribute(supervisor);
		return "supervisor/index";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") int id, ModelMap model) {
	    Supervisor supervisor = supervisorService.findSupervisorById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
	    model.addAttribute("supervisor", supervisor);
	    return "supervisor/edit";
	}
	
	@PostMapping("/update/{id}")
	public String updateSupervisor(@PathVariable Integer id,
	                               @ModelAttribute Supervisor updatedSupervisor,
	                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
	                               @RequestParam(value = "imgUrl", required = false) String existingImgUrl) throws IOException {

	    // Nếu có ảnh mới → upload và gán lại đường dẫn
	    if (imageFile != null && !imageFile.isEmpty()) {
	        String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        String uploadDir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	        Files.createDirectories(Paths.get(uploadDir)); // Đảm bảo thư mục tồn tại
	        Path path = Paths.get(uploadDir, filename);
	        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        updatedSupervisor.setImgUrl("/image/" + filename);
	    } else {
	        // Nếu không chọn ảnh mới → dùng ảnh cũ
	    	
	        updatedSupervisor.setImgUrl(existingImgUrl);
	    }

	    supervisorService.saveSupervisor(id, updatedSupervisor);
	    return "redirect:/supervisors/detail/" + id;
	}

	
	@PostMapping("/upload-image")
	@ResponseBody
	public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
	    String filename = StringUtils.cleanPath(file.getOriginalFilename());
	    String uploadDir = new ClassPathResource("static/image/uploads/").getFile().getAbsolutePath();
	    Path path = Paths.get(uploadDir, filename);
	    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	    // Trả về URL ảnh để TinyMCE chèn vào nội dung
	    String fileUrl = "/image/uploads/" + filename;
	    return Map.of("location", fileUrl);
	}
}

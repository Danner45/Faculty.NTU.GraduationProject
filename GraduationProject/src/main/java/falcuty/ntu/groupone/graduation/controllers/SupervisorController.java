package falcuty.ntu.groupone.graduation.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
	public String updateSupervisor(@PathVariable("id") int id,
	                               @ModelAttribute("supervisor") Supervisor updatedSupervisor,
	                               @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
	    if (!imageFile.isEmpty()) {
	        // Lưu ảnh vào thư mục static/image/
	        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        String uploadDir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	        Path path = Paths.get(uploadDir, fileName);
	        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	        // Gán đường dẫn ảnh cho supervisor
	        updatedSupervisor.setImgUrl("/image/" + fileName);
	    }

	    supervisorService.saveSupervisor(id,updatedSupervisor);
	    return "redirect:/supervisors/detail/" + id;
	}
}

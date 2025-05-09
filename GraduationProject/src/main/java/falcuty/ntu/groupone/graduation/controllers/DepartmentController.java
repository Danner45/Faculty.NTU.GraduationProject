package falcuty.ntu.groupone.graduation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import falcuty.ntu.groupone.graduation.models.Department;
import falcuty.ntu.groupone.graduation.services.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	

	
}

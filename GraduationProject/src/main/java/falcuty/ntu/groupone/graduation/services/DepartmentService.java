package falcuty.ntu.groupone.graduation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Department;
import falcuty.ntu.groupone.graduation.repositories.IDepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	IDepartmentRepository myDepartmentRepository;
	public List<Department> allSP()
		{
			return myDepartmentRepository.findAll();
		}
}

package com.example.controller;

import java.io.FileReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.EmployeeEntity;
import com.example.model.MoveEntity;
import com.example.view.Employee;
import com.example.view.Move;
import com.google.gson.Gson;

@Controller
public class loginController {
	
	@Autowired
	private com.example.dao.EmployeeDao EmployeeDao;
	
//	@RequestMapping(value = "/login",method = RequestMethod.POST)
//	@ResponseBody
//	public Boolean checkCredentials(@RequestBody Employee employee) {
//
//		EmployeeEntity employeeEntity = EmployeeDao.findByUsernameAndPassword(employee.getUsername(), employee.getPassword());
//		if(employeeEntity != null) {
//			return true;
//		}
//
//		return false;
//	}

	private Employee EmployeeEntityToEmployee(EmployeeEntity employeeEntity) {
		
		Employee employeeObject = new Employee();
		employeeObject.setPassword(employeeEntity.getPassword());
		employeeObject.setUsername(employeeEntity.getUsername());
		return employeeObject;
	}

	private EmployeeEntity EmployeeToEmployeeEntity(Employee employee) {
		
		EmployeeEntity EmployeeEntityObject = new EmployeeEntity();
		EmployeeEntityObject.setPassword(employee.getPassword());
		EmployeeEntityObject.setUsername(employee.getUsername());
		
		return EmployeeEntityObject;
	}
	
	private List<Employee> EmployeeEntityToEmployee(List<EmployeeEntity> employeeEntity) {
		
		List<Employee> employeesList = new ArrayList<Employee>(); 
		
		for(int i = 0; i < employeeEntity.size(); i++) {
			Employee employeeObject = new Employee();
			employeeObject.setPassword(employeeEntity.get(i).getPassword());
			employeeObject.setUsername(employeeEntity.get(i).getUsername());
			employeesList.add(employeeObject);
		}
		
		return employeesList;
	}

}


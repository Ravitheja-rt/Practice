package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.entity.Employee;
import com.cg.service.EmployeeService;

@Controller
public class AppController {
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		List<Employee> employeeList = service.listAll();
		model.addAttribute("employeeList", employeeList);
		
		return "employeeList";
	}
	
	@RequestMapping(value = "/newEmployeeForm", method = RequestMethod.GET)
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "new_employee";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		service.save(employee);
		
		return "redirect:/employeeList";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditEmployeeForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_employee");
		
		Employee employee = service.get(id);
		mav.addObject("employee", employee);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/employeeList";
	}
}

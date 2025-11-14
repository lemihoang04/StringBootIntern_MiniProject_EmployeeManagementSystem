package com.example.MiniProject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MiniProject.model.Employee;
import com.example.MiniProject.repository.DepartmentRepository;
import com.example.MiniProject.repository.EmployeeRepository;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/employees") 
public class EmployeeWebController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeWebController.class);
	
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeWebController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/list")
    public String showEmployeeList(@RequestParam(required = false) String keyword, Model model) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeRepository.findByNameContainingIgnoreCaseOrDepartment_NameContainingIgnoreCase(keyword, keyword);
            model.addAttribute("keyword", keyword);
        } else {
            employees = employeeRepository.findAll();
        }
        // Đưa danh sách employees vào 'Model' để View có thể truy cập
        model.addAttribute("employees", employees); 
        return "list-employees"; 
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Tạo một đối tượng Employee rỗng để binding dữ liệu [cite: 60]
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "add-employee-form"; 
    }
    
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            logger.warn("Lỗi Validation khi lưu nhân viên: {}", bindingResult.getAllErrors());
            return "add-employee-form";
        }

        if (employee.getId() == null) {
            logger.info("Đang tạo nhân viên mới: {}", employee.getName());
        } else {
            logger.info("Đang cập nhật nhân viên ID {}: {}", employee.getId(), employee.getName());
        }

        try {
            employeeRepository.save(employee);
            logger.info("Lưu nhân viên thành công.");
        } catch (Exception e) {
            logger.error("Lỗi khi lưu nhân viên vào DB: {}", e.getMessage());
        }
        
        return "redirect:/employees/list";
    }
}

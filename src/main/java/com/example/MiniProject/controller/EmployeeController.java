package com.example.MiniProject.controller;


import com.example.MiniProject.model.Employee;
import com.example.MiniProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees") 
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    // Inject Repository
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // 1. Lấy danh sách nhân viên (CRUD)
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. Thêm nhân viên mới (CRUD)
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        // save() của JPA thực hiện cả thêm mới và cập nhật
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // 3. Lấy thông tin nhân viên theo ID (CRUD)
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
    }
    
    // 4. Bổ sung chức năng tìm kiếm nhân viên theo tên (Module 4.6)
    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam String name) {
        // Dùng phương thức đã định nghĩa trong Repository
        return employeeRepository.findByNameContaining(name);
    }
    
    // 5. Thêm API DELETE (CRUD)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Status 204
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Status 404
        }
    }
    
    // Bạn cần thêm API UPDATE (PUT/PATCH) tương tự
}
package com.example.MiniProject.controller;


import com.example.MiniProject.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/api/employees") 
public class EmployeeController {

 private static List<Employee> employeeList = new ArrayList<>();
 private static AtomicLong idCounter = new AtomicLong(3);

 static {
     employeeList.add(new Employee(1L, "Nguyen Van A", "a@test.com", "IT"));
     employeeList.add(new Employee(2L, "Tran Thi B", "b@test.com", "HR"));
 }

 /**
  * API 1: Lấy danh sách nhân viên (GET /api/employees)
  */
 @GetMapping
 public List<Employee> getAllEmployees() {
     return employeeList;
 }

 /**
  * API 2: Thêm nhân viên mới (POST /api/employees)
  */
 @PostMapping
 public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

     employee.setId(idCounter.incrementAndGet());
     employeeList.add(employee);
     
     return new ResponseEntity<>(employee, HttpStatus.CREATED);
 }

 /**
  * API 3: Lấy thông tin nhân viên theo ID (GET /api/employees/{id})
  * Dùng @PathVariable để lấy ID từ URL.
  */
 @GetMapping("/{id}")
 public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
     Employee employee = employeeList.stream()
         .filter(e -> e.getId().equals(id))
         .findFirst()
         .orElse(null);

     if (employee != null) {
         return ResponseEntity.ok(employee); // Status 200 OK
     } else {
         // Trả về Status 404 Not Found (Sẽ được xử lý tốt hơn ở Module 5)
         return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
     }
 }
 
 // Bạn có thể thêm API Update và Delete tương tự
}

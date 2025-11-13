package com.example.MiniProject.repository;

import com.example.MiniProject.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Thêm chức năng tìm kiếm theo tên hoặc phòng ban [cite: 46]
    // Spring Data JPA sẽ tự động tạo truy vấn từ tên phương thức
    List<Employee> findByNameContainingIgnoreCaseOrDepartment_NameContainingIgnoreCase(String name, String departmentName);

    // Tìm kiếm theo tên (ví dụ cho Module 4.6)
    List<Employee> findByNameContaining(String name);
}

package com.example.MiniProject.repository;

import com.example.MiniProject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Kế thừa JpaRepository để có sẵn các phương thức CRUD cơ bản
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    // Thêm chức năng tìm kiếm theo tên hoặc phòng ban [cite: 46]
    // Spring Data JPA sẽ tự động tạo truy vấn từ tên phương thức
}

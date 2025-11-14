package com.example.MiniProject.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.MiniProject.model.Department;
import com.example.MiniProject.repository.DepartmentRepository;

@Component 
public class DataInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    public DataInitializer(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       
        if (departmentRepository.count() == 0) {
            System.out.println(">>> Đang khởi tạo dữ liệu phòng ban (Seeding departments)...");

            Department it = new Department();
            it.setName("IT (Công nghệ)");
            departmentRepository.save(it);

            Department hr = new Department();
            hr.setName("HR (Nhân sự)");
            departmentRepository.save(hr);

            Department sales = new Department();
            sales.setName("Sales (Kinh doanh)");
            departmentRepository.save(sales);
            
            System.out.println(">>> Đã khởi tạo xong dữ liệu.");
        }
    }
}

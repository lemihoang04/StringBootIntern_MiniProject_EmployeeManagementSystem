package com.example.MiniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Thiết lập mối quan hệ n-1 (Many-to-One) với Department
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false) // Tên cột khóa ngoại trong bảng employee
    private Department department; // Tham chiếu đến đối tượng Department
    
	public Employee(Long id, String name, String email, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

    // Getters và Setters (Bạn nên tự thêm vào hoặc dùng Lombok)
    
}
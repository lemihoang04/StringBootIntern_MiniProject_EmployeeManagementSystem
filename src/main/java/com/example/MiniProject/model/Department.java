package com.example.MiniProject.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Thiết lập mối quan hệ 1-n (One-to-Many) với Employee
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Department(Long id, String name, Set<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.employees = employees;
	}

    
}

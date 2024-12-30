package com.example.Krishna.Department.repositories;

import com.example.Krishna.Department.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

}

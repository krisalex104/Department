package com.example.Krishna.Department.services;

import com.example.Krishna.Department.dtos.request.DepartmentRequestDto;
import com.example.Krishna.Department.dtos.response.DepartmentResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface DepartmentService {

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto getDepartmentById(Long departmentId);

    DepartmentResponseDto saveDepartment(DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto updateDepartmentById(Long departmentId, DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto updatePartialDepartment(Long departmentId, Map<String, Object> updatedMap);

    Boolean deleteDepartment(Long departmentId);
}

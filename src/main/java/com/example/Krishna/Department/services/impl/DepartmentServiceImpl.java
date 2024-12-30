package com.example.Krishna.Department.services.impl;

import com.example.Krishna.Department.dtos.request.DepartmentRequestDto;
import com.example.Krishna.Department.dtos.response.DepartmentResponseDto;
import com.example.Krishna.Department.entities.Department;
import com.example.Krishna.Department.exceptions.ResourceNotFoundException;
import com.example.Krishna.Department.repositories.DepartmentRepository;
import com.example.Krishna.Department.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> modelMapper.map(department,DepartmentResponseDto.class)).toList();
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long departmentId) {
        isExits(departmentId);
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        return modelMapper.map(departmentOptional.get(),DepartmentResponseDto.class);
    }

    @Override
    public DepartmentResponseDto saveDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department=modelMapper.map(departmentRequestDto,Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment,DepartmentResponseDto.class);
    }

    @Override
    public DepartmentResponseDto updateDepartmentById(Long departmentId, DepartmentRequestDto departmentRequestDto) {
        isExits(departmentId);
        Department department = modelMapper.map(departmentRequestDto, Department.class);
        department.setId(departmentId);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment,DepartmentResponseDto.class);
    }

    @Override
    public DepartmentResponseDto updatePartialDepartment(Long departmentId, Map<String, Object> updatedMap) {
        isExits(departmentId);
        Department department = departmentRepository.findById(departmentId).get();
        updatedMap.forEach((field,value)->{
            Field fieldsToBeUpdated = ReflectionUtils.findRequiredField(Department.class, field);
            fieldsToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldsToBeUpdated,department,value);
        });
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment,DepartmentResponseDto.class);
    }

    @Override
    public Boolean deleteDepartment(Long departmentId) {
        isExits(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    private void isExits(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists){
            throw new ResourceNotFoundException("Department not exists with id :"+departmentId);
        }
    }

}

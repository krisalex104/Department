package com.example.Krishna.Department.controllers;

import com.example.Krishna.Department.dtos.request.DepartmentRequestDto;
import com.example.Krishna.Department.dtos.response.DepartmentResponseDto;
import com.example.Krishna.Department.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody @Valid DepartmentRequestDto departmentRequestDto){
        DepartmentResponseDto departmentResponseDto = departmentService.saveDepartment(departmentRequestDto);
        return new ResponseEntity<>(departmentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments(){
        List<DepartmentResponseDto> allDepartments = departmentService.getAllDepartments();
        return ResponseEntity.ok(allDepartments);

    }

    @RequestMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id){
        DepartmentResponseDto department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartmentById(@PathVariable Long id, @RequestBody @Valid DepartmentRequestDto departmentRequestDto){
        DepartmentResponseDto department = departmentService.updateDepartmentById(id, departmentRequestDto);
        return ResponseEntity.ok(department);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> updatePartialDepartmentById(@PathVariable Long id , @RequestBody Map<String, Object> updateData){
        DepartmentResponseDto department = departmentService.updatePartialDepartment(id, updateData);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id){
        Boolean deleted = departmentService.deleteDepartment(id);
        return ResponseEntity.ok(deleted);
    }
}

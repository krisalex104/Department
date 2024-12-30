package com.example.Krishna.Department;

import com.example.Krishna.Department.dtos.request.DepartmentRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentRequestDtoTest {

    @Autowired
    private Validator validator;

    @Test
    public void testValidation() {
        DepartmentRequestDto dto = new DepartmentRequestDto();
        dto.setTitle(""); // Invalid
        dto.setActive(true);

        Set<ConstraintViolation<DepartmentRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        for (ConstraintViolation<DepartmentRequestDto> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}

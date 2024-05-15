/*
package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.elastic.dto.Employee;
import com.hesabbook.elastic.repository.EmployeeRepository;
import com.hesabbook.entity.expense.Expense;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public BusinessResponse findByPrimaryUserId() {
        BusinessResponse businessResponse = new BusinessResponse();
        Iterable<Employee> accountDetailResponse = employeeRepository.findAll();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/all")
    public BusinessResponse findByPrimaryUserId(@RequestBody Employee employee) {
        BusinessResponse businessResponse = new BusinessResponse();
        Employee accountDetailResponse = employeeRepository.save(employee);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }
}
*/

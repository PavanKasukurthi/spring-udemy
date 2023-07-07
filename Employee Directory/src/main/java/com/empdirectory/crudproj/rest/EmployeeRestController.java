package com.empdirectory.crudproj.rest;

import com.empdirectory.crudproj.entity.Employee;
import com.empdirectory.crudproj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;

    //expose employees and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee e = employeeService.findById(employeeId);

        if(e == null){
            throw new RuntimeException("Employee not found with id " + employeeId);
        }
        return e;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //just in case they pass an id in JSON... set id to 0
        //this is to force save a new item... instead of update

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee dbEmployee = employeeService.findById(employeeId);

        if(dbEmployee == null){
            throw new RuntimeException("Employee not found with id " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id " + employeeId;
    }
}

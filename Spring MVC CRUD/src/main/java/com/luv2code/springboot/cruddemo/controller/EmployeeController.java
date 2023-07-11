package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // load employee data
    @Autowired
    private EmployeeService employeeService;

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        //get the employees from the database
    List<Employee> theEmployees = employeeService.findAll();


        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create the model attribute to bind the form data
        Employee employee = new Employee();

        //Our Thymeleaf template will access this data for binding form data
        theModel.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        //save the employee
        employeeService.save(employee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
}










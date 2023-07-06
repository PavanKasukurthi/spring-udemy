package com.empdirectory.crudproj.dao;

import com.empdirectory.crudproj.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}

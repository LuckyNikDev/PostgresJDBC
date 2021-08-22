package com.example;

import com.example.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(1, "Niknev", 34, "nik@gmail.com");
        List<Employee> employeeList = new ArrayList<>();
        JdbcDAO jdbcDAO = new JdbcDAO();

        jdbcDAO.create(employee);

//        employeeList = jdbcDAO.readAll();
//        System.out.println(employeeList);

//        Employee employee1 = jdbcDAO.read(1);
//        System.out.println(employee1);

//        jdbcDAO.update(1, employee);

//        jdbcDAO.delete(1);
    }
}

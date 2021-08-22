package com.example;

import com.example.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDAO {
    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            System.out.println("Error connect");
            throwables.printStackTrace();
        }
    }

    public void create(Employee employee) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO train VALUES(?, ?, ?, ?)");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Error create");
            throwables.printStackTrace();
        }
    }

    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM train");
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));

                employees.add(employee);
            }
        } catch (SQLException throwables) {
            System.out.println("Error getAll");
            throwables.printStackTrace();
        }
        return employees;
    }

    public Employee read(int id) {
        Employee employee = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM train WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setAge(resultSet.getInt("age"));
            employee.setEmail(resultSet.getString("email"));
        } catch (SQLException throwables) {
            System.out.println("Error getAll");
            throwables.printStackTrace();
        }
        return employee;
    }

    public void update(int id, Employee updatedEmployee) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE train SET id=?, name=?, age=?, email=? WHERE id=?");
            preparedStatement.setInt(1, updatedEmployee.getId());
            preparedStatement.setString(2, updatedEmployee.getName());
            preparedStatement.setInt(3, updatedEmployee.getAge());
            preparedStatement.setString(4, updatedEmployee.getEmail());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Error update");
            throwables.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM train WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Error delete");
            throwables.printStackTrace();
        }
    }
}

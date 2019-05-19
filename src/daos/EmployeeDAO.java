/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Employee;
import daos.idaos.IEmployeeDAO;

/**
 *
 * @author Sekar Ayu Safitri
 */
public class EmployeeDAO implements IEmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> listEmployee = new ArrayList<Employee>();
        String query = "SELECT * FROM EMPLOYEES";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt(1), resultSet.getString("2"),
                        resultSet.getString("3"), resultSet.getString("4"), resultSet.getString("5"));
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setEmail(resultSet.getString(3));
                employee.setHire(resultSet.getString(4));
                employee.setSalary(resultSet.getString(5));
                listEmployee.add(employee);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return listEmployee;
    }

    @Override
    public List<Employee> getById(int id) {
        List<Employee> listEmployee = new ArrayList<Employee>();
        String query = "SELECT * FROM EMPLOYEES WHERE employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt(1), resultSet.getString("2"),
                        resultSet.getString("3"), resultSet.getString("4"), resultSet.getString("5"));
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setEmail(resultSet.getString(3));
                employee.setHire(resultSet.getString(4));
                employee.setSalary(resultSet.getString(5));
                listEmployee.add(employee);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return listEmployee;
    }

    @Override
    public List<Employee> search(String key) {
        List<Employee> listEmployee = new ArrayList<Employee>();
        String query = "SELECT * FROM EMPLOYEES WHERE first_name LIKE (?) OR employee_id LIKE (?) "
                + "OR email LIKE (?) OR hire_date LIKE (?) OR salary LIKE (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + key + "%");
            preparedStatement.setString(2, "%" + key + "%");
            preparedStatement.setString(3, "%" + key + "%");
            preparedStatement.setString(4, "%" + key + "%");
            preparedStatement.setString(5, "%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt(1), resultSet.getString("2"),
                        resultSet.getString("3"), resultSet.getString("4"), resultSet.getString("5"));
                listEmployee.add(employee);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return listEmployee;
    }

    @Override
    public boolean insert(Employee region) {
        boolean result = false;
        String query = "INSERT INTO EMPLOYEES(employee_id, first_name, email, hire_date, salary) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2,region.getName());
            preparedStatement.setString(3, region.getEmail());
            preparedStatement.setString(4, region.getHire());
            preparedStatement.setString(5, region.getSalary());
            preparedStatement.executeQuery();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Employee region) {
        boolean result = false;
        String query = "UPDATE EMPLOYEES SET first_name = ?, email = ?, hire_date = ?, salary = ? + WHERE employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, region.getName());
            preparedStatement.setString(3, region.getEmail());
            preparedStatement.setString(4, region.getHire());
            preparedStatement.setString(5, region.getSalary());
            preparedStatement.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        String query = "DELETE FROM EMPLOYEES WHERE employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt(1), resultSet.getString("2"),
                        resultSet.getString("3"), resultSet.getString("4"), resultSet.getString("5"));
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setEmail(resultSet.getString(3));
                employee.setHire(resultSet.getString(4));
                employee.setSalary(resultSet.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

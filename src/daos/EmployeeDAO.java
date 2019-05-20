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
                listEmployee.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getInt(8), resultSet.getFloat(9), resultSet.getInt(10), resultSet.getInt(11)));
            }
        } catch (Exception e) {
            e.getStackTrace(); //menampilkan error di console
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
                listEmployee.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getInt(8), resultSet.getFloat(9), resultSet.getInt(10), resultSet.getInt(11)));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return listEmployee;
    }

    @Override
    public List<Employee> search(String key) {
        List<Employee> listEmployee = new ArrayList<Employee>();
        String query = "SELECT * FROM EMPLOYEES WHERE employee_id LIKE (?) OR first_name LIKE (?) OR last_name LIKE (?) "
                + "OR email LIKE (?) OR phone_number LIKE (?) OR hire_date LIKE (?) OR job_id LIKE (?) OR salary LIKE (?) OR commission_pct LIKE (?) OR manager_id LIKE (?) OR department_id LIKE (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                preparedStatement.setString(1, "%" + key + "%");
//                preparedStatement.setString(2, "%" + key + "%");
//                preparedStatement.setString(3, "%" + key + "%");
//                preparedStatement.setString(4, "%" + key + "%");
//                preparedStatement.setString(6, "%" + key + "%");
//                preparedStatement.setString(7, "%" + key + "%");
//                preparedStatement.setString(8, "%" + key + "%");
//                preparedStatement.setString(9, "%" + key + "%");
//                preparedStatement.setString(10, "%" + key + "%");
//                preparedStatement.setString(11, "%" + key + "%");
                listEmployee.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getInt(8), resultSet.getFloat(9), resultSet.getInt(10), resultSet.getInt(11)));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return listEmployee;
    }

    @Override
    public boolean insert(Employee employee) {
        boolean result = false;
        String query = "INSERT INTO EMPLOYEES(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, "
                + "salary, commission_pct, manager_id, department_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setString(6, employee.getHire());
            preparedStatement.setString(7, employee.getJobId());
            preparedStatement.setInt(8, employee.getSalary());
            preparedStatement.setFloat(9, employee.getCommission());
            preparedStatement.setInt(10, employee.getManagerId());
            preparedStatement.setInt(11, employee.getDepartmentId());
            preparedStatement.executeQuery();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Employee employee) {
        boolean result = false;
        String query = "UPDATE EMPLOYEES SET first_name = ?, last_name = ? email = ?, phone_number = ?, "
                + "hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, departmen_id = ? WHERE employee_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setString(6, employee.getHire());
            preparedStatement.setString(7, employee.getJobId());
            preparedStatement.setInt(8, employee.getSalary());
            preparedStatement.setFloat(9, employee.getCommission());
            preparedStatement.setInt(10, employee.getManagerId());
            preparedStatement.setInt(11, employee.getDepartmentId());
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
                Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getInt(8), resultSet.getFloat(9), resultSet.getInt(10), resultSet.getInt(11));
                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setEmail(resultSet.getString(4));
                employee.setPhoneNumber(resultSet.getString(5));
                employee.setHire(resultSet.getString(6));
                employee.setJobId(resultSet.getString(7));
                employee.setSalary(resultSet.getInt(8));
                employee.setCommission(resultSet.getFloat(9));
                employee.setManagerId(resultSet.getInt(10));
                employee.setDepartmentId(resultSet.getInt(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

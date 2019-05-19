/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.idaos;

import java.util.List;
import models.Employee;

/**
 *
 * @author Sekar Ayu Safitri
 */
public interface IEmployeeDAO {

    public List<Employee> getAll();

    public List<Employee> getById(int id);

    public List<Employee> search(String key);

    public boolean insert(Employee employee);

    public boolean update(Employee employee);

    public boolean delete(int id);
}

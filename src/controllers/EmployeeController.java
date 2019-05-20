/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.Employee;
import controllers.icontrollers.IEmployeeController;
import daos.idaos.IEmployeeDAO;

/**
 *
 * @author Sekar Ayu Safitri
 */
public class EmployeeController implements IEmployeeController {

    private IEmployeeDAO irdao;

    public EmployeeController(IEmployeeDAO irdao) {
        this.irdao = irdao;
    }

    @Override
    public List<Employee> getAll() {
        return irdao.getAll();
    }

    @Override
    public List<Employee> getById(String id) {
        return irdao.getById(Integer.parseInt(id));
    }

    @Override
    public List<Employee> search(String key) {
        return irdao.search(key);
    }

    @Override
    public String update(String id, String firstName, String email, String hire, String salary) {
        String result = "";
        Employee employee = new Employee(firstName, Integer.parseInt(id), email, hire, Integer.parseInt(salary));
        if (irdao.update(employee)) {
            result = "Data Berhasil diupdate";
        } else {
            result = "Data Gagal diupdate";
        }
        return result;
    }

    @Override
    public String delete(String id) {
        String result = "";
        if (irdao.delete(Integer.parseInt(id))) {
            result = "Data Berhasil dihapus";
        } else {
            result = "Data Gagal dihapus";
        }
        return result;
    }

    @Override
    public String insert(String id, String firstName, String lastName, String email, String phone, String hire, String jobId, String salary, String commission, String managerId, String departmentId) {
        String result = "";
        Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email, phone, hire, jobId, Integer.parseInt(salary), Float.parseFloat(commission), Integer.parseInt(managerId), Integer.parseInt(departmentId));
        if (irdao.insert(employee)) {
            result = "Data Berhasil disimpan";
        } else {
            result = "Data Gagal disimpan";
        }
        return result;
    }
}

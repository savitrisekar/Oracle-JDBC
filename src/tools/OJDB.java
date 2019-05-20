/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import daos.EmployeeDAO;
import models.Employee;
import daos.idaos.IEmployeeDAO;

/**
 *
 * @author Sekar Ayu Safitri
 */
public class OJDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Employee employee = new Employee();
//        employee.setId(0);
//        employee.setName("Employee");
//        employee.setEmail("employee@gmail.com");
//        employee.setHire("10/05/2019");
//        employee.setSalary("3500000");
//        System.out.println(employee.getId() + "" + employee.getName() + ""
//                + employee.getEmail() + "" + employee.getHire() + "" + employee.getSalary());
//                                          
//        Employee mEmployee = new Employee(1, "Employee New", "employee@gmail.com", "10/05/2019", "3500000");
//         System.out.println(employee.getId() + "" + employee.getName() + ""
//                + employee.getEmail() + "" + employee.getHire() + "" + employee.getSalary());

        DBConnection connection = new DBConnection();
        System.out.println(connection.getConnection());

        //manual test dao insert
        IEmployeeDAO irdao = new EmployeeDAO(connection.getConnection());
        //Employee employee1 = new Employee(31, "My Employee", "employee@gmail.com", "10/05/2019", "3500000");
        //System.out.println(irdao.insert(employee1));
        IEmployeeDAO mIrdao = new EmployeeDAO(connection.getConnection());
        for (Employee employee2 : mIrdao.search("A")) {
            System.out.println(employee2.getId());
            System.out.println(employee2.getFirstName());
            System.out.println(employee2.getEmail());
            System.out.println(employee2.getHire());
            System.out.println(employee2.getSalary());
        }

        //manual test getAll
//        IEmployeeDAO mIrdao = new EmployeeDAO(connection.getConnection());
//        for (Employee employee2 : mIrdao.getAll()) {
//            System.out.println(employee2.getId());
//            System.out.println(employee2.getName());
//            System.out.println(employee2.getEmail());
//            System.out.println(employee2.getHire());
//            System.out.println(employee2.getSalary());
//        }
    }
}

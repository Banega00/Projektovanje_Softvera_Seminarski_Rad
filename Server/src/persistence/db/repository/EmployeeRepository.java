/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Employee;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author Bane
 */
public class EmployeeRepository implements DBRepository<Employee, Long>{
    private Connection connection;

    @Override
    public List<Employee> getAll() throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM EMPLOYEE e LEFT JOIN account a ON e.id=a.employeeId;");
        ResultSet rs = ps.executeQuery();
        
        List<Employee> employeeList = new ArrayList<>();
        while(rs.next()){
            Employee e = new Employee(rs.getLong("e.id"), rs.getString("e.ImePrezime"), rs.getString("e.JMBG"), rs.getString("e.email"));
            long accId = rs.getLong("a.id");
            if(!rs.wasNull()){
                boolean isAdmin = rs.getBoolean("isAdmin");
                if(isAdmin)continue;
                e.setAccount(new Account(accId, rs.getString("a.username"), rs.getString("a.password"),rs.getBoolean("isAdmin"),rs.getBoolean("active"), e));
            }
            employeeList.add(e);
        }
        rs.close();
        ps.close();
        
        return employeeList;
    }

    @Override
    public void add(Employee t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Employee t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Employee t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.List;
import model.Employee;
import persistence.db.repository.EmployeeRepository;

/**
 *
 * @author Bane
 */
public class GetEmployeesSO extends AbstractSO{
    private EmployeeRepository employeeRepository;
    public GetEmployeesSO() {
        this.employeeRepository = new EmployeeRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        //no payload data for this operation
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        List<Employee> employees = this.employeeRepository.getAll();
        return employees;
    }
}

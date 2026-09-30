package com.sarthak.springboot.cruddemo.dao;

import com.sarthak.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    //define field for entitymanager
    private EntityManager entityManager;
    //setup constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        //create query JPQL
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
        //execute query and result list
        List<Employee> employees = theQuery.getResultList();
        //return results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class,theId);
        //return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //save emp
        //does save if it does not exist or update it
        Employee dbEmpoyee = entityManager.merge(theEmployee);
        //return dbemp
        return dbEmpoyee;
    }

    @Override
    public void deleteById(int theId) {
    //find emp by id
        Employee theEmployee = entityManager.find(Employee.class, theId);
        entityManager.remove(theEmployee);
    }
}

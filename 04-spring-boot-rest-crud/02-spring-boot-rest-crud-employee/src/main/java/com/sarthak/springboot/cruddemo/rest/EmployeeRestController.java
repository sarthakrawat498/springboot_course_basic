package com.sarthak.springboot.cruddemo.rest;

import com.sarthak.springboot.cruddemo.dao.EmployeeDAO;
import com.sarthak.springboot.cruddemo.entity.Employee;
import com.sarthak.springboot.cruddemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private JsonMapper jsonMapper;
    //expose "/employees" and return list of employees
    public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper){
        employeeService = theEmployeeService;
        jsonMapper = theJsonMapper;
    }
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theEmployee;
    }

    //add mapping for Post /employee - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //if id is passed in JSON , set it to 0
        //this is to force a save of new item ... instead of updating
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //add mapping for PATCH
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String,Object> patchPayload){
        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        //throw exception if request body contains id ,so that user cannot change the primary key
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }
        Employee patchedEmployee = jsonMapper.updateValue(tempEmployee,patchPayload);
        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }

}

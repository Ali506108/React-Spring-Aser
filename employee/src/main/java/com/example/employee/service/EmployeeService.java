package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public Employee postEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> geyAllEmployees(){
        return  employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw  new EntityNotFoundException("Employee with id " + id + " not found" );
        }

        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee UpdateEmployee(Long id,Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee existEmployee = optionalEmployee.get();

            existEmployee.setEmail(employee.getEmail());
            existEmployee.setName(employee.getName());
            existEmployee.setPhone(employee.getPhone());
            existEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existEmployee);
        }
        return null;
    }

}

package com.AngApp.Service;

import com.AngApp.DTO.EmployeeDto;
import com.AngApp.Entity.Employee;
import com.AngApp.ExceptionHandling.ResourceNotFoundException;
import com.AngApp.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<EmployeeDto> listEmployees() {
        List<Employee> all = employeeRepository.findAll();
        return all.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());

    }
    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }
    public EmployeeDto deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
        
        return modelMapper.map(employee, EmployeeDto.class);
    }
    public EmployeeDto getEmployeeById(Long id){
        return employeeRepository.findById(id).map(employee -> modelMapper.map(employee, EmployeeDto.class)).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }



}

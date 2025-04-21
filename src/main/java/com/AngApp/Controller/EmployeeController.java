package com.AngApp.Controller;

import com.AngApp.DTO.EmployeeDto;
import com.AngApp.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/Add")
    public ResponseEntity<?>addEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
        return  new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/listAll")
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeDto> employeeDtos = employeeService.listEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }



}

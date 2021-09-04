package bd.info.habib.ibcscodechallenge.controller;

import bd.info.habib.ibcscodechallenge.dto.request.EmployeeRequest;
import bd.info.habib.ibcscodechallenge.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge.dto.response.EmployeeResponse;
import bd.info.habib.ibcscodechallenge.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Controller Class for Employee Management
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    //Get all the list of employee
    @GetMapping
    private ResponseEntity<BasicApiResponse<List<EmployeeResponse>>> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    //Create a new employee
    @PostMapping
    public ResponseEntity<ApiMessageResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }

    //Get a single employee by id
    @GetMapping("/{employeeId}")
    public ResponseEntity<BasicApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    //Edit an employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiMessageResponse> editEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                                           @PathVariable Long employeeId){
        return employeeService.editEmployee(employeeId, employeeRequest);
    }

    //Delete an employee by id
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiMessageResponse> deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}

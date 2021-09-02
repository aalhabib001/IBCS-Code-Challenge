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

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    private ResponseEntity<BasicApiResponse<List<EmployeeResponse>>> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    @PostMapping
    public ResponseEntity<ApiMessageResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiMessageResponse> editEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                                           @PathVariable Long employeeId){
        return employeeService.editEmployee(employeeId, employeeRequest);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiMessageResponse> deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}

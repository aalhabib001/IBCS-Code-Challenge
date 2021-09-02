package bd.info.habib.ibcscodechallenge.controller;

import bd.info.habib.ibcscodechallenge.dto.request.DepartmentRequest;
import bd.info.habib.ibcscodechallenge.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge.dto.response.DepartmentResponse;
import bd.info.habib.ibcscodechallenge.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<BasicApiResponse<List<DepartmentResponse>>> getDepartments(){
        return departmentService.getDepartments();
    }

    @PostMapping
    public ResponseEntity<ApiMessageResponse> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest){
        return departmentService.createDepartment(departmentRequest);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<ApiMessageResponse> editDepartment(@PathVariable Long departmentId,
                                                             @RequestBody DepartmentRequest departmentRequest){
        return departmentService.editDepartment(departmentId, departmentRequest);
    }
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<ApiMessageResponse> deleteDepartment(@PathVariable Long departmentId){
        return departmentService.deleteDepartment(departmentId);
    }
}

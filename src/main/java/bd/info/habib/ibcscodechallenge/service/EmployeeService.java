package bd.info.habib.ibcscodechallenge.service;

import bd.info.habib.ibcscodechallenge.dto.request.EmployeeRequest;
import bd.info.habib.ibcscodechallenge.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge.dto.response.EmployeeResponse;
import bd.info.habib.ibcscodechallenge.model.DepartmentModel;
import bd.info.habib.ibcscodechallenge.model.EmployeeModel;
import bd.info.habib.ibcscodechallenge.repository.DepartmentRepository;
import bd.info.habib.ibcscodechallenge.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Class for employee management
@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    //Getting all the list of employee
    public ResponseEntity<BasicApiResponse<List<EmployeeResponse>>> getEmployeeList() {
        List<EmployeeModel> employeeModels = employeeRepository.findAll();

        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (EmployeeModel employeeModel : employeeModels) {
            EmployeeResponse employeeResponse = new EmployeeResponse(employeeModel.getId(), employeeModel.getCode(),
                    employeeModel.getName(), employeeModel.getDateOfBirth(), employeeModel.getGender(),
                    employeeModel.getMobile(), employeeModel.getDepartmentModel().getName());

            employeeResponses.add(employeeResponse);
        }

        if (employeeModels.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee Found");
        } else {
            return new ResponseEntity<>(new BasicApiResponse<>(200, "Employees Found", employeeResponses),
                    HttpStatus.OK);
        }
    }

    //Create a new employee
    public ResponseEntity<ApiMessageResponse> addEmployee(EmployeeRequest employeeRequest) {
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(employeeRequest.getDepartmentId());
        if (departmentModelOptional.isPresent()) {
            DepartmentModel departmentModel = departmentModelOptional.get();

            EmployeeModel employeeModel = new EmployeeModel(0L, employeeRequest.getCode(), employeeRequest.getName(),
                    employeeRequest.getDateOfBirth(), employeeRequest.getGender(), employeeRequest.getMobile(),
                    departmentModel);

            employeeRepository.save(employeeModel);

            return new ResponseEntity<>(new ApiMessageResponse(201, "Employee Added"), HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: " + employeeRequest.getDepartmentId());
        }
    }

    //Edit employee
    public ResponseEntity<ApiMessageResponse> editEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
        if (employeeModelOptional.isPresent()) { //null checking
            EmployeeModel employeeModel = employeeModelOptional.get();

            Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(employeeRequest.getDepartmentId());
            if (departmentModelOptional.isPresent()) {
                DepartmentModel departmentModel = departmentModelOptional.get();

                employeeModel.setCode(employeeRequest.getCode());
                employeeModel.setName(employeeRequest.getName());
                employeeModel.setDateOfBirth(employeeRequest.getDateOfBirth());
                employeeModel.setMobile(employeeRequest.getMobile());
                employeeModel.setGender(employeeRequest.getGender());
                employeeModel.setDepartmentModel(departmentModel);

                return new ResponseEntity<>(new ApiMessageResponse(200, "Employee Edit Successful"), HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: "
                        + employeeRequest.getDepartmentId());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
                    + employeeId);
        }
    }

    //Delete employee by id
    public ResponseEntity<ApiMessageResponse> deleteEmployee(Long employeeId) {
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
        if (employeeModelOptional.isPresent()) {
            EmployeeModel employeeModel = employeeModelOptional.get();

            employeeRepository.delete(employeeModel);

            return new ResponseEntity<>(new ApiMessageResponse(200, "Employee Delete Successful"), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
                    + employeeId);
        }
    }

    //Get a single employee by id
    public ResponseEntity<BasicApiResponse<EmployeeResponse>> getEmployeeById(Long employeeId) {
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findById(employeeId);
        if (employeeModelOptional.isPresent()) {
            EmployeeModel employeeModel = employeeModelOptional.get();

            EmployeeResponse employeeResponse = new EmployeeResponse(employeeModel.getId(), employeeModel.getCode(),
                    employeeModel.getName(), employeeModel.getDateOfBirth(), employeeModel.getGender(),
                    employeeModel.getMobile(), employeeModel.getDepartmentModel().getName());

            return new ResponseEntity<>(new BasicApiResponse<>(200, "Employee Found", employeeResponse),
                    HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee found with ID: "
                    + employeeId);
        }
    }
}

package bd.info.habib.ibcscodechallenge.service;

import bd.info.habib.ibcscodechallenge.dto.request.DepartmentRequest;
import bd.info.habib.ibcscodechallenge.dto.response.ApiMessageResponse;
import bd.info.habib.ibcscodechallenge.dto.response.BasicApiResponse;
import bd.info.habib.ibcscodechallenge.dto.response.DepartmentResponse;
import bd.info.habib.ibcscodechallenge.model.DepartmentModel;
import bd.info.habib.ibcscodechallenge.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public ResponseEntity<BasicApiResponse<List<DepartmentResponse>>> getDepartments() {
        List<DepartmentModel> departmentModels = departmentRepository.findAll();

        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        for (DepartmentModel departmentModel : departmentModels) {
            DepartmentResponse departmentResponse = new DepartmentResponse(departmentModel.getId(),
                    departmentModel.getName(), departmentModel.getActive());
            departmentResponses.add(departmentResponse);
        }

        if (departmentModels.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Departments Found");
        } else {
            return new ResponseEntity<>(new BasicApiResponse<>(200, "Departments Found",
                    departmentResponses), HttpStatus.OK);
        }

    }

    public ResponseEntity<ApiMessageResponse> createDepartment(DepartmentRequest departmentRequest) {
        DepartmentModel departmentModel = new DepartmentModel(0L, departmentRequest.getName(), departmentRequest.getActive());

        departmentRepository.save(departmentModel);

        return new ResponseEntity<>(new ApiMessageResponse(201, "Department Created"), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiMessageResponse> editDepartment(Long departmentId, DepartmentRequest departmentRequest) {
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(departmentId);
        if (departmentModelOptional.isPresent()) {
            DepartmentModel departmentModel = departmentModelOptional.get();

            departmentModel.setName(departmentRequest.getName());
            departmentModel.setActive(departmentRequest.getActive());

            departmentRepository.save(departmentModel);

            return new ResponseEntity<>(new ApiMessageResponse(200, "Department info edit Successful"),
                    HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: " + departmentId);
        }
    }

    public ResponseEntity<ApiMessageResponse> deleteDepartment(Long departmentId) {
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(departmentId);
        if (departmentModelOptional.isPresent()) {
            DepartmentModel departmentModel = departmentModelOptional.get();

            departmentRepository.delete(departmentModel);

            return new ResponseEntity<>(new ApiMessageResponse(200, "Department info delete Successful"),
                    HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Department found with ID: " + departmentId);
        }
    }
}

package bd.info.habib.ibcscodechallenge.dto.response;

import bd.info.habib.ibcscodechallenge.model.DepartmentModel;
import bd.info.habib.ibcscodechallenge.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;

    private String code;

    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date dateOfBirth;

    private Gender gender;

    private String mobile;

    private String departmentName;
}

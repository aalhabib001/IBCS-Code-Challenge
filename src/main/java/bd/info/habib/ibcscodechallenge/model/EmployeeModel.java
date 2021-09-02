package bd.info.habib.ibcscodechallenge.model;

import bd.info.habib.ibcscodechallenge.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_MODEL")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Size(min = 4, max = 4)
    @Column(name = "CODE", unique = true, length = 4)
    private String code;

    @Size(min = 3, max = 35)
    @Column(name = "NAME", nullable = false, length = 35)
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "MOBILE")
    private String mobile;

    @ManyToOne(cascade = CascadeType.ALL)
    private DepartmentModel departmentModel;
}

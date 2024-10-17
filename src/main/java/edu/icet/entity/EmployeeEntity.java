package edu.icet.entity;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class EmployeeEntity {

    private String EmpId;

    private String EmpName;

    private String EmpCompany;

    private String EmpEmail;
}

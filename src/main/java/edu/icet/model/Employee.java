package edu.icet.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Employee {

    private String EmpId;

    private String EmpName;

    private String EmpCompany;

    private String EmpEmail;
}

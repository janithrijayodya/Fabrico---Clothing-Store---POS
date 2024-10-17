package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplierEntity {
    private String SupplierID;

    private String SupplierName;

    private String SupplierCompany;

    private String SupplierEmail;
}

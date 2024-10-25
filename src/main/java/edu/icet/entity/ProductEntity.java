package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductEntity {

    private String productID;
    private String productName;
    private String size;
    private Double price;
    private Integer quantity;
    private String type;
    private  String supID;
//    private byte[] image;
}

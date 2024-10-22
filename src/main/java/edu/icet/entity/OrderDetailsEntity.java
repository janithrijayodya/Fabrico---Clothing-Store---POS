package edu.icet.entity;

import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsEntity {
    private String order_id;
    private String product_id;
    private Integer quantity;
    private Double total_amount;
    private String payment_type;
}

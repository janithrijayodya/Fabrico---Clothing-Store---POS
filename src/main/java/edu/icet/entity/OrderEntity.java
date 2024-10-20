package edu.icet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderEntity {
    private String order_id;
    private String date;
    private String emp_id;
}

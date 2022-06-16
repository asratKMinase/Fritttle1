package com.revature.frittte.order;

import com.revature.frittte.customer.Customer;
import com.revature.frittte.food.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInitializer {


    private int id;
    private String orderDate;
    private String itemName;
    private String comment;
    private String customerUsername;

}
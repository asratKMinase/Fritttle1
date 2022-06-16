package com.revature.frittte.order;

import com.revature.frittte.customer.Customer;
import com.revature.frittte.food.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "order_o")
public class OrderData {
    @Id
    private int id;

    @Column(name = "order_date")
    private String orderDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Food", referencedColumnName = "item_name")
    private Food itemName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer", referencedColumnName = "username")
    private Customer customer_username;

    @Column(name = "comment_t")
    private String comment;
}

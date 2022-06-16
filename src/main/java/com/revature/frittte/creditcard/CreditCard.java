package com.revature.frittte.creditcard;


import com.revature.frittte.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @Column(name = "cc_number", nullable = false)
    private long creditCardNumber;
    @Column(name = "cc_name" ,nullable = false)
    private String creditCardName;
    private int cvv;
    @Column(name = "exp_date" ,nullable = false)
    private String expDate;
    @Column(name = "limit_l" ,nullable = false)
    private int limit;
    @ManyToOne(optional = false)
    @JoinColumn(name = "Customer", referencedColumnName = "username")
    //@Column(name = "customer_username" ,nullable = false)
    private Customer customerUsername;

}

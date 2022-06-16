package com.revature.frittte.util.web.dto;

import com.revature.frittte.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CCInitializer {

    private String creditCardNumber;
    private String creditCardName;
    private int cvv;
    private String expDate;
    private double limit;
    private String customerUsername;

}

package com.revature.frittte.util.web.dto;

import com.revature.frittte.customer.Customer;
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
public class CCInitializer {

    private long creditCardNumber;
    private String creditCardName;
    private int cvv;
    private String expDate;
    private int limit;
    private Customer customerUsername;

}

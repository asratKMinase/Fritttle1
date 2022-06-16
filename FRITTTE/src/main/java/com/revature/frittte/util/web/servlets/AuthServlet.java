package com.revature.frittte.util.web.servlets;
import com.revature.frittte.customer.Customer;
import com.revature.frittte.customer.CustomerService;
import com.revature.frittte.util.web.dto.LoginCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet {

    private final CustomerService customerServices;

    @Autowired
    public AuthServlet(CustomerService customerServices){
        this.customerServices = customerServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorizeCustomer(@RequestBody LoginCreds loginCreds, HttpSession httpSession){
        Customer authCustomer = customerServices.authenticateCustomer(loginCreds.getUsername(), loginCreds.getPassword());
        httpSession.setAttribute("authCustomer", authCustomer);
    }

    @DeleteMapping
    public void logout(HttpSession session){
        session.invalidate();
    }

}

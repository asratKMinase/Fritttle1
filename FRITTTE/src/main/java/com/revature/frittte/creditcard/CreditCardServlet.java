package com.revature.frittte.creditcard;

import com.revature.frittte.customer.Customer;
import com.revature.frittte.customer.CustomerService;
import com.revature.frittte.util.web.dto.CCInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class CreditCardServlet {
    private final CreditCardService creditCardService;
    private final CustomerService customerService;

    public CreditCardServlet(CreditCardService creditCardService, CustomerService customerService) {
        this.creditCardService = creditCardService;
        this.customerService = customerService;
    }

    @CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/addCreditCard")
    public ResponseEntity<CreditCard> CreateCreditCard(@RequestBody CCInitializer newCreditCard, HttpSession req){

        CreditCard newCC = new CreditCard();
        Customer authCustomer = (Customer) req.getAttribute("authCustomer");
        //CCInitializer initCC = mapper.readValue(req.getInputStream(), CCInitializer.class); // from JSON to Java Object (Pokemon)

            newCC.setCreditCardNumber(newCreditCard.getCreditCardNumber());
            newCC.setCreditCardName(newCreditCard.getCreditCardName());
            newCC.setCvv(newCreditCard.getCvv());
            newCC.setExpDate(newCreditCard.getExpDate());
            newCC.setLimit(newCreditCard.getLimit());
            newCC.setCustomerUsername(customerService.readById(newCreditCard.getCustomerUsername()));

        CreditCard creditCard = creditCardService.create(newCC);

        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);

    }


    @GetMapping("/findCard")
    public ResponseEntity<CreditCard> FindCreditCard(@RequestParam String findCreditCard){
        ResponseEntity<CreditCard> creditcard = null;
        try {
            CreditCard creditCard = creditCardService.findById(findCreditCard);
            return new ResponseEntity<>(creditCard, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

    }

    @CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
    @DeleteMapping("/delete")
    public void deleteCreditCard(@RequestParam String creditCardNumber) {
        boolean newCreditCard = creditCardService.delete(creditCardNumber);
    }
//    @DeleteMapping("/deleteCreditCard")
//    public void DeleteCreditCard(@RequestBody int creditCardNumber){
//        creditCardService.delete(creditCardNumber);
//    }
}

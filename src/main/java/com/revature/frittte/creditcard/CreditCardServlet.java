package com.revature.frittte.creditcard;

import com.revature.frittte.customer.Customer;
import com.revature.frittte.util.web.dto.CCInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class CreditCardServlet {

    private final CreditCardService creditCardService;

    public CreditCardServlet(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }


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
            newCC.setCustomerUsername(authCustomer);

        CreditCard creditCard = creditCardService.create(newCC);

        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);

    }

    @GetMapping("/findCard")
    public ResponseEntity<CreditCard> FindCreditCard(@RequestBody int findCreditCard){
        CreditCard creditCard = creditCardService.findById(findCreditCard);
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }
    @DeleteMapping("/deleteCreditCard")
    public void DeleteCreditCard(@RequestBody String deletedCreditCard){
        creditCardService.delete(deletedCreditCard);
    }
}

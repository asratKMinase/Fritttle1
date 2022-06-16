package com.revature.frittte.order;


import com.revature.frittte.customer.Customer;
import com.revature.frittte.customer.CustomerService;
import com.revature.frittte.food.Food;
import com.revature.frittte.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin //Resource Sharing, by default it allows all "*"
public class OrderServlet {
    private final OrderServices orderServices;
    private final FoodService foodServices;
    private final CustomerService customerService;
    @Autowired
    public OrderServlet(OrderServices orderServices, FoodService foodServices,
                        CustomerService customerService) {

        this.orderServices = orderServices;
        this.foodServices = foodServices;
        this.customerService = customerService;

    }
    @PostMapping("/order")
    public ResponseEntity<OrderData> CreateOrder(@RequestBody OrderInitializer initOrder, HttpSession req){

        OrderData newOrder = new OrderData();
        Customer authCustomer = (Customer) req.getAttribute("authCustomer");

        //CCInitializer initCC = mapper.readValue(req.getInputStream(), CCInitializer.class); // from JSON to Java Object (Pokemon)
        Food itemName = foodServices.readById(initOrder.getItemName());
        newOrder.setId(initOrder.getId());
        newOrder.setOrderDate(initOrder.getOrderDate());
        newOrder.setItemName(itemName);
        newOrder.setComment(initOrder.getComment());
        newOrder.setCustomer_username(customerService.readById(initOrder.getCustomerUsername()));



        OrderData persistedOrder = orderServices.create(newOrder);

        return new ResponseEntity<>(persistedOrder, HttpStatus.CREATED);

    }

    @GetMapping("/findAllMyOrders/{username}")
    public ResponseEntity<List> findAllMyOrders(@PathVariable String username) {
        return new ResponseEntity<>(orderServices.findAllMyOrders(username), HttpStatus.OK);
    }

}
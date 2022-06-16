package com.revature.frittte.order;


import com.revature.frittte.customer.Customer;
import com.revature.frittte.food.Food;
import com.revature.frittte.food.FoodService;
//import com.revature.frittte.util.web.dto.CCInitializer;
//import com.revature.frittte.util.web.dto.OrderInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin //Resource Sharing, by default it allows all "*"
public class OrderServlet {
    private final OrderServices orderServices;
    private final FoodService foodServices;
    @Autowired
    public OrderServlet(OrderServices orderServices, FoodService foodServices) {

        this.orderServices = orderServices;
        this.foodServices = foodServices;

    }
    @PostMapping("/order")
    public ResponseEntity<OrderData> CreateCreditCard(@RequestBody OrderInitializer initOrder, HttpSession req){

        OrderData newOrder = new OrderData();
        Customer authCustomer = (Customer) req.getAttribute("authCustomer");

        //CCInitializer initCC = mapper.readValue(req.getInputStream(), CCInitializer.class); // from JSON to Java Object (Pokemon)
        Food itemName = foodServices.readById(initOrder.getItemName());
        newOrder.setId(initOrder.getId());
        newOrder.setOrderDate(initOrder.getOrderDate());
        newOrder.setItemName(itemName);
        newOrder.setCustomer_username(authCustomer);
        newOrder.setComment(initOrder.getComment());



        OrderData persistedOrder = orderServices.create(newOrder);

        return new ResponseEntity<>(persistedOrder, HttpStatus.CREATED);

    }
    @CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
    @PutMapping("/updateOrder")
    public ResponseEntity<OrderData> updateCustomer(@RequestBody OrderData order) {
        OrderData newOrderData= orderServices.update(order);
        return new ResponseEntity<>(newOrderData, HttpStatus.OK);
    }

}

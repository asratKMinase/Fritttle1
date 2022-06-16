package com.revature.frittte.order;

import com.revature.frittte.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderServices {
    private OrderDao orderDao;
    @Autowired // not really necessary, but good practice
    public OrderServices(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderData create(OrderData newOrder) {

        OrderData persistedOrder = orderDao.save(newOrder);
        return persistedOrder;
    }


    public boolean validateInput(OrderData orderData) {
        if(orderData == null) return false;
        if(orderData.getId() < 0) return false;
        if(orderData.getOrderDate() == null || orderData.getOrderDate().trim().equals("")) return false;
        if(orderData.getItemName() == null) return false;
        if(orderData.getCustomer_username() == null) return false;
        return orderData.getComment() != null || orderData.getComment() == null;
    }
}

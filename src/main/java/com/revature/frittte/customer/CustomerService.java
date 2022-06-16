package com.revature.frittte.customer;
import com.revature.frittte.customer.CustomerDao;
import com.revature.frittte.exception.AuthenticationException;
import com.revature.frittte.exception.InvalidRequestException;
import com.revature.frittte.exception.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> findAll(){
        List<Customer> customers = (List<Customer>) customerDao.findAll();
        return customers;
    }
        public Customer readById(String id) {
        Customer customer = customerDao.findById(id).get();
        return customer;
    }

    public Customer update(Customer updatedCustomer) {
        customerDao.save(updatedCustomer);
        return updatedCustomer;
    }
    public boolean delete(String username) {
        customerDao.deleteById(username);
        return true;
    }

    public boolean validateUsernameNotUsed(String username){
        return customerDao.existsById(username);
    }

    public Customer create(Customer newCustomer){
        if(!validateInput(newCustomer)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateUsernameNotUsed(newCustomer.getUsername())){
            throw new InvalidRequestException("Username is already in use. Please try again with another email or login into previous made account.");
        }
        Customer persistedTrainer = customerDao.save(newCustomer);

        if(persistedTrainer == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration");
        }
        return persistedTrainer;
    }

    public boolean validateInput(Customer newCustomer) {
        if(newCustomer == null) return false;
        if(newCustomer.getUsername()== null || newCustomer.getUsername().trim().equals("")) return false;
        if(newCustomer.getPassword() == null || newCustomer.getPassword().trim().equals("")) return false;
        if(newCustomer.getFname() == null || newCustomer.getFname().trim().equals("")) return false;
        if(newCustomer.getLname() == null || newCustomer.getLname().trim().equals("")) return false;
         return newCustomer.getDob() != null || !newCustomer.getDob().trim().equals("");
    }

    public Customer authenticateCustomer(String username, String password){

        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
        }

        Customer authenticatedCustomer = customerDao.authenticateCustomer(username, password);

        if (authenticatedCustomer == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedCustomer;

    }

}

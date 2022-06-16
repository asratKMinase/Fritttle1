package com.revature.frittte.food;

import com.revature.frittte.customer.Customer;
import com.revature.frittte.exception.AuthenticationException;
import com.revature.frittte.exception.InvalidRequestException;
import com.revature.frittte.exception.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FoodService {
    private FoodDao foodDao;
    @Autowired
    public FoodService(FoodDao foodDao){
        this.foodDao = foodDao;
    }


    public List<Food> findAll(){
        List<Food> foods = (List <Food>) foodDao.findAll();
        return foods;
    }

    public  boolean deleteById(String id){
      foodDao.deleteById(id);
      return true;



    }

    public Food readById(String id){
        Food food = foodDao.findById(id).get();
        return food;

    }
    public Food update(Food updatedFood) {
        foodDao.save(updatedFood);
        return updatedFood;
    }

    public boolean validateUsernameNotUsed(String id){
        return foodDao.existsById(id);
    }

    public Food create(Food newFoodItem){
        if(!validateInput(newFoodItem)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("New FoodItem was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)

        Food persistedFood = foodDao.save(newFoodItem);

        if(persistedFood == null){
            throw new ResourcePersistanceException("newFoodItem was not persisted to the database upon registration");
        }
        return persistedFood;
    }



    public boolean validateInput(Food newFoodItem) {
        if(newFoodItem == null) return false;
        if(newFoodItem.getId()== null || newFoodItem.getId().trim().equals("")) return false;
        if(newFoodItem.getItemName()== null || newFoodItem.getItemName().trim().equals("")) return false;
        if(newFoodItem.getCost()<0) return false;
        if(newFoodItem.getWeight()<0) return false;
        if((newFoodItem.getVolume() <0 )) return false;
        return((newFoodItem.isFrozen() != true)|| (newFoodItem.isFrozen() != false )) ;
        }

    public Food authenticateFood(String id, String item_name){

        if( id == null || id.trim().equals("")|| item_name == null || item_name.trim().equals("")) {
            throw new InvalidRequestException("Either id or item_name is an invalid entry. Please try logging in again");
        }

        Food authenticatedFood = foodDao.authenticateFood(id, item_name);

        if (authenticatedFood == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedFood;

    }



}

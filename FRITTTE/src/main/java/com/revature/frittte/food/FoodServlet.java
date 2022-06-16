package com.revature.frittte.food;

import com.revature.frittte.customer.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FoodServlet {

    private FoodService foodService;

    public FoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/newFoodItem")
    public ResponseEntity<Food> createFoodItem(@RequestBody Food newFood){
        Food food = foodService.create(newFood);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @GetMapping("/findFoodItem")
    public ResponseEntity<Food> findFoodItem(@RequestParam String id){
        Food food = foodService.readById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/findAllFoodItems")
    public ResponseEntity<List> findAllFoodItems(){
        return new ResponseEntity<>(foodService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/updateFoodItem")
    public ResponseEntity<Food> updateFoodItem(@RequestBody Food food) {
        Food updatedFoodItem= foodService.update(food);
        return new ResponseEntity<>(updatedFoodItem, HttpStatus.OK);
    }




    @DeleteMapping("/deleteFoodItem")
    public void deleteFoodItem(@RequestParam String id) {

        boolean deleteFood = foodService.deleteById(id);
    }

}

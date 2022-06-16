package com.revature.frittte.util.web.servlets;


import com.revature.frittte.customer.Customer;
import com.revature.frittte.exception.AuthenticationException;
import com.revature.frittte.exception.InvalidRequestException;
import com.revature.frittte.util.web.dto.CCInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/test")
public class TestServlet {

    @GetMapping("/1")
    public @ResponseBody String test(){
        return "Welcome to the wonderful world of Spring";
    }

    @GetMapping("/2")
    public @ResponseBody String test2(){
        return "Hey, this is another get method. nice right.";
    }

    @PostMapping("/post-test")
    public @ResponseBody Customer postTest(@RequestBody Customer customer){
        return customer;
    }

    @PostMapping("/post-pokemon")
    public @ResponseBody CCInitializer postTestPokemon(@RequestBody CCInitializer creditecard){
        return creditecard;
    }

    @GetMapping("/testException")
    public void testException(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }

    @GetMapping("/testException1")
    public void testException1(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }
    @GetMapping("/testException2")
    public void testException2(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }
    @GetMapping("/testException3")
    public void testException3(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }

    @GetMapping("/testException4")
    public void testException4(){
        throw new AuthenticationException("Boooo don't hit this unauthorized endpoint");
    }


}

package com.validations.demo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/showForm")
    public String showForm(Model theModel){
        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @GetMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "customer-form";
        }else {
            return "customer-confirmation";
        }
    }
}

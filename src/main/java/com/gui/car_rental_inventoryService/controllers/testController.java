package com.gui.car_rental_inventoryService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class testController {
    @GetMapping("/status")
    public String status(){
        return "Inventory service working...";
    }
}

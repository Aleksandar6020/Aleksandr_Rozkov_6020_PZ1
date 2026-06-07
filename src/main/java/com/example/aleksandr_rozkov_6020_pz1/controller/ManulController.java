package com.example.aleksandr_rozkov_6020_pz1.controller;

import com.example.aleksandr_rozkov_6020_pz1.service.ApplicationStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManulController {

    private final ApplicationStorageService storageService;

    public ManulController() {
        storageService = new ApplicationStorageService();
    }

    @GetMapping("/manuls")
    public String showManuls(Model model) {

        model.addAttribute(
                "manuls",
                storageService.getManuls()
        );

        return "manuls";
    }
}
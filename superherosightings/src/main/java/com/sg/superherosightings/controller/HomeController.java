/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.SightingService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author nacer
 */
@Controller
public class HomeController {
    
    private final SightingService sightingService;
    public HomeController(SightingService sightingService){
        this.sightingService = sightingService;
    }
    
    @GetMapping("/")
    public String displayIndex(Model model) {
        
        return "index";
    }
    
}

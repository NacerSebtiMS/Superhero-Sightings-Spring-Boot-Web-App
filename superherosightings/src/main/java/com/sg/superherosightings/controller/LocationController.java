/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author nacer
 */
@Controller
public class LocationController {
    
    private final LocationService locationService;
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        
        return "locations";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.service.SightingService;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author nacer
 */
@Controller
public class SightingController {
    
    private final SightingService sightingService;
    public SightingController(SightingService sightingService){
        this.sightingService = sightingService;
    }
    
    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingService.getAllSightings();
        HashMap<Sighting,Hero> heroSightings = sightingService.mapHeroSightings(sightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroSightings", heroSightings);
        return "sightings";
    }
    
    @GetMapping("/sightings/addSighting")
    public String displayAddSightings(Model model) {       
        return "/sightings/addSighting";
    }
    
    @PostMapping("/sightings/addSighting")
    public String addSighting(HttpServletRequest request){
        
        
        //sightingService.createSighting();
        
        return "redirect:/sightings/addSighting";
    }
}

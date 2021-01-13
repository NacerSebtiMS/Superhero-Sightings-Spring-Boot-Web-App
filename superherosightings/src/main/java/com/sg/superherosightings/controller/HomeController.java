/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.service.HomeService;
import com.sg.superherosightings.service.SightingService;
import java.util.HashMap;
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
    private final HomeService homeService;
    public HomeController(SightingService sightingService, HomeService homeService){
        this.sightingService = sightingService;
        this.homeService = homeService;
    }
    
    private String googleMapUrl; 
    
    @GetMapping("/")
    public String displayIndex(Model model) {
        final int SIGHTINGS_PER_PAGE = 10;
        List<Sighting> sightings = sightingService.getLastSightings(SIGHTINGS_PER_PAGE);
        HashMap<Sighting,Hero> heroSightings = sightingService.mapHeroSightings(sightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroSightings", heroSightings);
        
        googleMapUrl = homeService.buildUrl(sightings);
        
        model.addAttribute("googleMapUrl", googleMapUrl);
        
        return "index";
    }
    
}

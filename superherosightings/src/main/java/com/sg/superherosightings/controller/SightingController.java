/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.SightingService;
import java.sql.Date;
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
    private final HeroService heroService;
    private final LocationService locationService;
    public SightingController(SightingService sightingService, HeroService heroService, LocationService locationService){
        this.sightingService = sightingService;
        this.heroService = heroService;
        this.locationService = locationService;
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
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        
        return "/sightings/addSighting";
    }
    
    @PostMapping("/sightings/addSighting")
    public String addSighting(HttpServletRequest request){
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Date date = Date.valueOf(request.getParameter("sightingDate"));
        
        Hero hero = heroService.getHeroById(heroId);
        Location location = locationService.getLocationById(locationId);
        
        Sighting sighting = sightingService.createSighting(hero, location, date);
        sightingService.addSighting(sighting);
        
        return "redirect:/sightings/addSighting";
    }
    
    @GetMapping("/sightings/deleteSighting")
    public String deleteSighting(Integer id) {
        sightingService.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @GetMapping("/sightings/editSighting")
    public String displayEditSighting(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Sighting sighting = sightingService.getSightingById(id);
        model.addAttribute("sighting", sighting);
        
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        
        return "sightings/editSighting";
    }
    
    @PostMapping("/sightings/editSighting")
    public String editSighting(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("sightingId"));
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Date date = Date.valueOf(request.getParameter("sightingDate"));
        
        Hero hero = heroService.getHeroById(heroId);
        Location location = locationService.getLocationById(locationId);
        
        Sighting sighting = sightingService.createSighting(hero, location, date);
        sighting.setId(id);
        sightingService.updateSighting(sighting);
        
        return "redirect:/sightings";
    }
}

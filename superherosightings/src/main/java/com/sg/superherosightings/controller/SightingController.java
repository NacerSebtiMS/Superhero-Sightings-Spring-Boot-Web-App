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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
    
    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();
    
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
        violations.clear();
        
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        
        model.addAttribute("errors", violations);
        
        return "/sightings/addSighting";
    }
    
    @PostMapping("/sightings/addSighting")
    public String addSighting(HttpServletRequest request, Model model){
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String dateString = request.getParameter("sightingDate");
        
        Date date = null;
        if(sightingService.isValidDate(dateString)){
            date = Date.valueOf(dateString);
        } else {
            model.addAttribute("dateError", "Empty or Invalid date.");
        }
        
        Hero hero = heroService.getHeroById(heroId);
        Location location = locationService.getLocationById(locationId);
        
        Sighting sighting = sightingService.createSighting(hero, location, date);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);
        if(violations.isEmpty() && date !=null){
            sightingService.addSighting(sighting);
        }
        
        model.addAttribute("errors", violations);
        
        
        return "redirect:/sightings/addSighting";
    }
    
    @GetMapping("/sightings/deleteSighting")
    public String deleteSighting(Integer id) {
        sightingService.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @GetMapping("/sightings/editSighting")
    public String displayEditSighting(HttpServletRequest request, Model model) {
        violations.clear();
        int id = Integer.parseInt(request.getParameter("id"));
        
        Sighting sighting = sightingService.getSightingById(id);
        model.addAttribute("sighting", sighting);
        
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        
        model.addAttribute("errors", violations);
        
        return "sightings/editSighting";
    }
    
    @PostMapping("/sightings/editSighting")
    public String editSighting(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("sightingId"));
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String dateString = request.getParameter("sightingDate");
        
        Date date = null;
        if(sightingService.isValidDate(dateString)){
            date = Date.valueOf(dateString);
        } else {
            model.addAttribute("dateError", "Empty or Invalid date.");
        }
        
        Hero hero = heroService.getHeroById(heroId);
        Location location = locationService.getLocationById(locationId);
        
        Sighting sighting = sightingService.createSighting(hero, location, date);
        sighting.setId(id);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);
        if(violations.isEmpty() && date !=null){
            sightingService.updateSighting(sighting);
            return "redirect:/sightings";
        } else {
            sighting = sightingService.getSightingById(sighting.getId());
            model.addAttribute("sighting", sighting);

            List<Hero> heros = heroService.getAllHeros();
            model.addAttribute("heros", heros);

            List<Location> locations = locationService.getAllLocations();
            model.addAttribute("locations", locations);
            
            model.addAttribute("errors", violations);
            
            return "sightings/editSighting";
        }  
        
        
    }
}

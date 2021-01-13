/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.service.LocationService;
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
public class LocationController {
    
    private final LocationService locationService;
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }
    
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    String latitudeError = null;
    String longitudeError = null;
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @GetMapping("/locations/addLocation")
    public String displayAddLocations(Model model) {
        model.addAttribute("errors", violations);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "/locations/addLocation";
    }
    
    @PostMapping("/locations/addLocation")
    public String addLocation(HttpServletRequest request, Model model){
        violations.clear();
        latitudeError = null;
        longitudeError = null;
        
        String name = request.getParameter("locationName");
        String stringLatitude = request.getParameter("latitude");
        String stringLongitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        double latitude = 0;
        if(locationService.isValidLatitude(stringLatitude)){
            latitude = Double.parseDouble(stringLatitude);
        } else {
            latitudeError = "Invalid or Empty Latitude";
        }
        
        double longitude = 0;
        if(locationService.isValidLongitude(stringLongitude)){
            longitude = Double.parseDouble(stringLongitude);
        } else {
            longitudeError = "Invalid or Empty Longitude";
        }
        
        Location location = locationService.createLocation(name, latitude, longitude, description, address);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);
        if(violations.isEmpty() && locationService.isValidLatitude(stringLatitude) && locationService.isValidLongitude(stringLongitude)){
            locationService.addLocation(location);
        }
        
        model.addAttribute("errors", violations);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "redirect:/locations/addLocation";
    }
    
    @GetMapping("/locations/deleteLocation")
    public String deleteLocation(Integer id) {
        locationService.deleteLocationById(id);
        return "redirect:/locations";
    }
    
    @GetMapping("/locations/editLocation")
    public String displayEditLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationService.getLocationById(id);
        
        model.addAttribute("location", location);
        
        model.addAttribute("errors", violations);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "locations/editLocation";
    }
    
    @PostMapping("/locations/editLocation")
    public String editLocation(HttpServletRequest request, Model model){
        violations.clear();
        latitudeError = null;
        longitudeError = null;
        
        int id = Integer.parseInt(request.getParameter("locationId"));
        String name = request.getParameter("locationName");
        String stringLatitude = request.getParameter("latitude");
        String stringLongitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        double latitude = 0;
        if(locationService.isValidLatitude(stringLatitude)){
            latitude = Double.parseDouble(stringLatitude);
        } else {
            latitudeError = "Invalid or Empty Latitude";
        }
        
        double longitude = 0;
        if(locationService.isValidLongitude(stringLongitude)){
            longitude = Double.parseDouble(stringLongitude);
        } else {
            longitudeError = "Invalid or Empty Longitude";
        }
        
        Location location = locationService.createLocation(name, latitude, longitude, description, address);
        location.setId(id);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);
        if(violations.isEmpty() && locationService.isValidLatitude(stringLatitude) && locationService.isValidLongitude(stringLongitude)){
            locationService.updateLocation(location);
            return "redirect:/locations";
        } else {
            model.addAttribute("location", locationService.getLocationById(location.getId()));
            model.addAttribute("errors", violations);
            model.addAttribute("latitudeError", latitudeError);
            model.addAttribute("longitudeError", longitudeError);
            return "locations/editLocation";
        }    
    }
    
    @GetMapping("locations/detailsLocation")
    public String displayDetailsLocation(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        
        Location location = locationService.getLocationById(id);
        model.addAttribute("location", location);
        
        List<Hero> heros = locationService.getHerosForLocation(location);
        model.addAttribute("heros", heros);
        
        return "locations/detailsLocation";
    }
}

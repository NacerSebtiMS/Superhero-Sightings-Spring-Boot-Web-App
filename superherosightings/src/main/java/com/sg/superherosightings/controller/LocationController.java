/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.service.LocationService;
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
public class LocationController {
    
    private final LocationService locationService;
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @GetMapping("/locations/addLocation")
    public String displayAddLocations(Model model) {       
        return "/locations/addLocation";
    }
    
    @PostMapping("/locations/addLocation")
    public String addLocation(HttpServletRequest request){
        String name = request.getParameter("locationName");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        Location location = locationService.createLocation(name, latitude, longitude, description, address);
        locationService.addLocation(location);
        
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
        return "locations/editLocation";
    }
    
    @PostMapping("/locations/editLocation")
    public String editLocation(HttpServletRequest request, Model model){
        String name = request.getParameter("locationName");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        Location location = locationService.createLocation(name, latitude, longitude, description, address);
        locationService.updateLocation(location);
        
        return "redirect:/locations";
    }
}

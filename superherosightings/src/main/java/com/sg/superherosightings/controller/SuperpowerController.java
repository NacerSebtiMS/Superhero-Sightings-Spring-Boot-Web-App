/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Superpower;
import com.sg.superherosightings.service.SuperpowerService;
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
public class SuperpowerController {
    
    private final SuperpowerService superpowerService;
    public SuperpowerController(SuperpowerService superpowerService){
        this.superpowerService = superpowerService;
    }
    
    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @GetMapping("/superpowers/addSuperpower")
    public String displayAddSuperpowers(Model model) { 
        model.addAttribute("errors", violations);
        
        return "/superpowers/addSuperpower";
    }
    
    @PostMapping("/superpowers/addSuperpower")
    public String addSuperpower(HttpServletRequest request, Model model){
        violations.clear();
        
        String name = request.getParameter("superpowerName");
        String description = request.getParameter("superpowerDescription");
        
        Superpower superpower = superpowerService.createSuperpower(name, description);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);
        if(violations.isEmpty()){
            superpowerService.addSuperpower(superpower);
        }
        
        model.addAttribute("errors", violations);
        
        
        return "redirect:/superpowers/addSuperpower";
    }
    
    @GetMapping("/superpowers/deleteSuperpower")
    public String deleteSuperpower(Integer id) {
        superpowerService.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }
    
    @GetMapping("/superpowers/editSuperpower")
    public String displayEditSuperpower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerService.getSuperpowerById(id);
        
        model.addAttribute("superpower", superpower);
        
        model.addAttribute("errors", violations);
        
        return "superpowers/editSuperpower";
    }
    
    @PostMapping("/superpowers/editSuperpower")
    public String editSuperpower(HttpServletRequest request, Model model){
        violations.clear();
        
        int id = Integer.parseInt(request.getParameter("superpowerId"));
        String name = request.getParameter("superpowerName");
        String description = request.getParameter("superpowerDescription");
        
        Superpower superpower = superpowerService.createSuperpower(name, description);
        superpower.setId(id);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);
        if(violations.isEmpty()){
            superpowerService.updateSuperpower(superpower);
            return "redirect:/superpowers";
        } else {
            superpower = superpowerService.getSuperpowerById(superpower.getId());
        
            model.addAttribute("superpower", superpower);
            
            model.addAttribute("errors", violations);
            
            return "superpowers/editSuperpower";
        }  
    }
    
    @GetMapping("superpowers/detailsSuperpower")
    public String displayDetailsSuperpower(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        
        Superpower superpower = superpowerService.getSuperpowerById(id);
        model.addAttribute("superpower", superpower);
        
        List<Hero> heros = superpowerService.getHerosForSuperpower(superpower);
        model.addAttribute("heros", heros);
        
        return "superpowers/detailsSuperpower";
    }
}

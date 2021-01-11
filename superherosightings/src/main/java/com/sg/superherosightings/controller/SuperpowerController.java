/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Superpower;
import com.sg.superherosightings.service.SuperpowerService;
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
public class SuperpowerController {
    
    private final SuperpowerService superpowerService;
    public SuperpowerController(SuperpowerService superpowerService){
        this.superpowerService = superpowerService;
    }
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @GetMapping("/superpowers/addSuperpower")
    public String displayAddSuperpowers(Model model) {       
        return "/superpowers/addSuperpower";
    }
    
    @PostMapping("/superpowers/addSuperpower")
    public String addSuperpower(HttpServletRequest request){
        
        
        //superpowerService.createSuperpower();
        
        return "redirect:/superpowers/addSuperpower";
    }
}

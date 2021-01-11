/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.service.HeroService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author nacer
 */
@Controller
public class HeroController {
    
    private final HeroService heroService;
    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }
    
    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        return "heroes";
    }
    
    @GetMapping("/heroes/addHero")
    public String displayAddHeroes(Model model) {       
        return "/heroes/addHero";
    }
}

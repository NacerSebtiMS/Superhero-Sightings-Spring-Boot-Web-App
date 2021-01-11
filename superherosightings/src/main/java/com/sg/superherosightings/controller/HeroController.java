/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Superpower;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.SuperpowerService;
import java.util.ArrayList;
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
public class HeroController {
    
    private final HeroService heroService;
    private final SuperpowerService superpowerService;
    public HeroController(HeroService heroService, SuperpowerService superpowerService){
        this.heroService = heroService;
        this.superpowerService = superpowerService;
    }
    
    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        return "heroes";
    }
    
    @GetMapping("/heroes/addHero")
    public String displayAddHeroes(Model model) {
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "/heroes/addHero";
    }
    
    @PostMapping("/heroes/addHero")
    public String addHero(HttpServletRequest request){
        String name = request.getParameter("heroName");
        boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        String description = request.getParameter("heroDescription");
        String[] superpowerIds = request.getParameterValues("superpowerId");
        
        List<Superpower> superpowers = new ArrayList<>();
        for(String superpowerId : superpowerIds) {
            superpowers.add(superpowerService.getSuperpowerById(Integer.parseInt(superpowerId)));
        }
        
        heroService.createHero(name,isHero,description,superpowers);
        
        return "redirect:/heroes/addHero";
    }
}

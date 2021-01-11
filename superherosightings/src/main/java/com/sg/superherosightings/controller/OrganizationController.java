/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.OrganizationService;
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
public class OrganizationController {
    
    private final OrganizationService organizationService;
    private final HeroService heroService;
    public OrganizationController(OrganizationService organizationService, HeroService heroService){
        this.organizationService = organizationService;
        this.heroService = heroService;
    }
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "organizations";
    }
    
    @GetMapping("/organizations/addOrganization")
    public String displayAddOrganizations(Model model) {
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        return "/organizations/addOrganization";
    }
    
    @PostMapping("/organizations/addOrganization")
    public String addOrganization(HttpServletRequest request){
        // String name, boolean isHero, String description, String address, String contact, List<Hero> heros
        String name = request.getParameter("organizationName");
        boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        String description = request.getParameter("organizationDescription");
        String address = request.getParameter("organizationAddress");
        String contact = request.getParameter("organizationContact");
        String[] heroIds = request.getParameterValues("heroId");
        
        List<Hero> heros = new ArrayList<>();
        for(String heroId : heroIds) {
            heros.add(heroService.getHeroById(Integer.parseInt(heroId)));
        }
        
        Organization organization = organizationService.createOrganization(name, isHero, description, address, contact, heros);
        organizationService.addOrganization(organization);
        
        return "redirect:/organizations/addOrganization";
    }
    
    @GetMapping("/organizations/deleteOrganization")
    public String deleteOrganization(Integer id) {
        organizationService.deleteOrganizationById(id);
        return "redirect:/organizations";
    }
    
    @GetMapping("/organizations/editOrganization")
    public String displayEditOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Organization organization = organizationService.getOrganizationById(id);
        model.addAttribute("organization", organization);
        
        List<Hero> heros = heroService.getAllHeros();
        model.addAttribute("heros", heros);
        
        return "organizations/editOrganization";
    }
}

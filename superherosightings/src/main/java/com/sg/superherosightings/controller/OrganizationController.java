/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.service.OrganizationService;
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
    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "organizations";
    }
    
    @GetMapping("/organizations/addOrganization")
    public String displayAddOrganizations(Model model) {       
        return "/organizations/addOrganization";
    }
    
    @PostMapping("/organizations/addOrganization")
    public String addOrganization(HttpServletRequest request){
        
        
        //organizationService.createOrganization();
        
        return "redirect:/organizations/addOrganization";
    }
}

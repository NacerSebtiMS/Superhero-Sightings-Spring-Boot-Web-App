/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.OrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        
        return "organizations";
    }
    
    
}

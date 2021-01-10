/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.SightingService;
import org.springframework.stereotype.Controller;

/**
 *
 * @author nacer
 */
@Controller
public class SightingController {
    
    private final SightingService sightingService;
    public SightingController(SightingService sightingService){
        this.sightingService = sightingService;
    }
    
    
    
}

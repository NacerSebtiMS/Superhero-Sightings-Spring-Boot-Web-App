/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;
import com.sg.superherosightings.service.HeroSightingsService;
import org.springframework.stereotype.Controller;

/**
 *
 * @author nacer
 */
@Controller
public class HeroController {
    
    private final HeroSightingsService service;
    public HeroController(HeroSightingsService service){
        this.service = service;
    }
    
}

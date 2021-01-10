/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.SuperpowerService;
import org.springframework.stereotype.Controller;

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
    
    
    
}

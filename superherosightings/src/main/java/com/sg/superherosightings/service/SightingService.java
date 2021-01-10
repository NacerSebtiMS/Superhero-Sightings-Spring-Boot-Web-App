/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperpowerDao;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class SightingService {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    // SERVICE FUNCTION
    
       
    // EXTERNAL DAO FUNCTIONS  
    Hero getHeroForSighting(Sighting sighting){
        return heroDao.getHeroForSighting(sighting);
    }
    // LOCAL DAO FUNCTIONS  
    public Sighting getSightingById(int id){
        return sightingDao.getSightingById(id);      
    }
    List<Sighting> getAllSightings(){
        return sightingDao.getAllSightings();
    }
    Sighting addHero(Sighting sighting){
        return sightingDao.addSighting(sighting);
    }
    void updateSighting(Sighting hero){
        sightingDao.updateSighting(hero);
    }
    void deleteSightingById(int id){
        sightingDao.deleteSightingById(id);
    }
}

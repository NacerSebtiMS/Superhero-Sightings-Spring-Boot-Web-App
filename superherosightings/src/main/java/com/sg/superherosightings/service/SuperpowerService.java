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
import com.sg.superherosightings.models.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class SuperpowerService {
    
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
    public List<Hero> getHerosForSuperpower(Superpower superpower){
        return heroDao.getHerosForSuperpower(superpower);
    }
    // LOCAL DAO FUNCTIONS  
    public Superpower getSuperpowerById(int id){
        return superpowerDao.getSuperpowerById(id);      
    }
    List<Superpower> getAllSuperpowers(){
        return superpowerDao.getAllSuperpowers();
    }
    Superpower addSuperpower(Superpower superpower){
        return superpowerDao.addSuperpower(superpower);
    }
    void updateSuperpower(Superpower superpower){
        superpowerDao.updateSuperpower(superpower);
    }
    void deleteSuperpowerById(int id){
        superpowerDao.deleteSuperpowerById(id);
    }
}

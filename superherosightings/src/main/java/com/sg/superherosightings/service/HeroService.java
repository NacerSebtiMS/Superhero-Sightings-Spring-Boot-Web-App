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
import com.sg.superherosightings.models.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class HeroService {
    
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
    List<Organization> getOrganizationsForHero(Hero hero){
        return organizationDao.getOrganizationsForHero(hero);
    }  
    // LOCAL DAO FUNCTIONS  
    public Hero getHeroById(int id){
        return heroDao.getHeroById(id);      
    }
    List<Hero> getAllHeros(){
        return heroDao.getAllHeros();
    }
    Hero addHero(Hero hero){
        return heroDao.addHero(hero);
    }
    void updateHero(Hero hero){
        heroDao.updateHero(hero);
    }
    void deleteHeroById(int id){
        heroDao.deleteHeroById(id);
    }
    
}

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
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class LocationService {
    
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
    List<Sighting> getSightingsForLocation(Location location){
        return sightingDao.getSightingsForLocation(location);
    }
    List<Hero> getHerosForLocation(Location location){
        return heroDao.getHerosForLocation(location);
    }
    // LOCAL DAO FUNCTIONS  
    public Location getLocationById(int id){
        return locationDao.getLocationById(id);      
    }
    public List<Location> getAllLocations(){
        return locationDao.getAllLocations();
    }
    public Location addHero(Location location){
        return locationDao.addLocation(location);
    }
    public void updateLocation(Location hero){
        locationDao.updateLocation(hero);
    }
    public void deleteLocationById(int id){
        locationDao.deleteLocationById(id);
    }
}

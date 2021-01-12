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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
    public Sighting createSighting(Hero hero, Location location, Date date){
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);

        return sighting;
    }
    
    public boolean isValidDate(String date){
        try{
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    // 2 functions to get last sightings and the heroes seen
    public List<Sighting> getLastSightings(int number){
        List<Sighting> sightings = sightingDao.getAllSightings();
        Collections.sort(sightings, new SortByDateDesc());
        if(number >= sightings.size()){
            return sightings;
        } else {
            List<Sighting> result = new ArrayList<>();
            for(int i=0;i<sightings.size();i++){
                result.add(sightings.get(i));
            }
            return result;
        }
    }
    public HashMap<Sighting, Hero> mapHeroSightings(List<Sighting> sightings){
        HashMap<Sighting, Hero> heroSightings = new HashMap<>();
        for(int i=0;i<sightings.size();i++){
            heroSightings.put(sightings.get(i), getHeroForSighting(sightings.get(i)));
        }
        return heroSightings;
    }
    
    public static final class SortByDateDesc implements Comparator<Sighting>{

        @Override
        public int compare(Sighting s1, Sighting s2) {
            return s2.getDate().compareTo(s1.getDate());
        }
        
    }
       
    // EXTERNAL DAO FUNCTIONS  
    public Hero getHeroForSighting(Sighting sighting){
        return heroDao.getHeroForSighting(sighting);
    }
    // LOCAL DAO FUNCTIONS  
    public Sighting getSightingById(int id){
        return sightingDao.getSightingById(id);      
    }
    public List<Sighting> getAllSightings(){
        return sightingDao.getAllSightings();
    }
    public Sighting addSighting(Sighting sighting){
        return sightingDao.addSighting(sighting);
    }
    public void updateSighting(Sighting sighting){
        sightingDao.updateSighting(sighting);
    }
    public void deleteSightingById(int id){
        sightingDao.deleteSightingById(id);
    }
}

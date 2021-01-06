/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.HeroLocation;
import com.sg.superherosightings.models.Location;
import java.util.List;

/**
 *
 * @author nacer
 */
public interface HeroLocationDao {
    
    HeroLocation getHeroLocationByHeroAndLocation(Hero hero, Location location);
    List<HeroLocation> getAllHeroLocations();
    HeroLocation addHeroLocation(HeroLocation heroLocation);
    void updateHeroLocation(HeroLocation heroLocation);
    void deleteHeroLocationById(int id);
    
    List<HeroLocation> getHeroLocationsForLocation(Location location);
}

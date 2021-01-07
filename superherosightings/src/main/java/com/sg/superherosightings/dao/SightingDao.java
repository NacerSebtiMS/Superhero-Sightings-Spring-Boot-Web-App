/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Location;
import java.util.List;

/**
 *
 * @author nacer
 */
public interface SightingDao {
    
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addHeroLocation(Sighting sighting);
    void updateHeroLocation(Sighting sighting);
    void deleteHeroLocationById(int id);
    
    List<Sighting> getSightingsForLocation(Location location);
}

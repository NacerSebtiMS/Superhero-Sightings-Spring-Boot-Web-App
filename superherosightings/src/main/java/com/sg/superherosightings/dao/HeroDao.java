/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.util.List;

/**
 *
 * @author nacer
 */
public interface HeroDao {
    
    Hero getHeroById(int id);
    List<Hero> getAllHeros();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroById(int id);
    
    List<Hero> getHerosForSuperpower(Superpower superpower);
    Hero getHeroForSighting(Sighting sighting);
    List<Hero> getHerosForLocation(Location location);
}

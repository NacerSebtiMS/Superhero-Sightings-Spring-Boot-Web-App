/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author nacer
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeroDaoDBTest {
    
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
    
    public HeroDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Hero> heros = heroDao.getAllHeros();
        for(Hero hero : heros){
            heroDao.deleteHeroById(hero.getId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations){
            locationDao.deleteLocationById(location.getId());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            organizationDao.deleteOrganizationById(organization.getId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings){
            sightingDao.deleteSightingById(sighting.getId());
        }

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers){
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testGetHeroById() {
    }
    
    @Test
    public void testGetAndAddHero(){
        
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower.setDescription("Test description");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();  
        
        Hero hero = new Hero();
        hero.setIsHero(true);
        hero.setDescription("Test description");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        /*
        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = locationDao.addLocation(location);
        
        Date date = new Date(123456);
               
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        sightings.add(sighting);
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        */
        Hero fromDao = heroDao.getHeroById(hero.getId());
        
        assertEquals(hero,fromDao);
    }

    /**
     * Test of getAllHeros method, of class HeroDaoDB.
     */
    @Test
    public void testGetAllHeros() {
    }

    /**
     * Test of addHero method, of class HeroDaoDB.
     */
    @Test
    public void testAddHero() {
    }

    /**
     * Test of updateHero method, of class HeroDaoDB.
     */
    @Test
    public void testUpdateHero() {
    }

    /**
     * Test of deleteHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testDeleteHeroById() {
    }

    /**
     * Test of getHerosForSuperpower method, of class HeroDaoDB.
     */
    @Test
    public void testGetHerosForSuperpower() {
    }

    /**
     * Test of getHeroForSighting method, of class HeroDaoDB.
     */
    @Test
    public void testGetHeroForSighting() {
    }
    
}

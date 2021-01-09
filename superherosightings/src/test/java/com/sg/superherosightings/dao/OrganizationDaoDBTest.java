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
public class OrganizationDaoDBTest {
    
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
    
    public OrganizationDaoDBTest() {
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
     * Test of getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetOrganizationById() {
    }
    
    @Test
    public void testGetAndAddOrganization(){

        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower.setDescription("Test description");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setIsHero(true);
        hero.setName("Test name");
        hero.setDescription("Test description");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setIsHero(true);
        organization.setDescription("Test description");
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
        
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower.setDescription("Test description");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setIsHero(true);
        hero.setName("Test name");
        hero.setDescription("Test description");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setIsHero(true);
        organization.setDescription("Test description");
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);
        
        // Organization 2
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Test name2");
        superpower2.setDescription("Test description2");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        
        List<Sighting> sightings2 = new ArrayList<>();        
        
        Hero hero2 = new Hero();
        hero2.setIsHero(false);
        hero2.setName("Test name2");
        hero2.setDescription("Test description2");
        hero2.setSuperpowers(superpowers2);
        hero2.setSightings(sightings2);
        hero2 = heroDao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("Test name2");
        location2.setLatitude(1.3);
        location2.setLongitude(5.36);
        location2.setDescription("Test description2");
        location2.setAddressInformation("Test address info2");
        location2 = locationDao.addLocation(location2);
        
        Date date2 = Date.valueOf("2017-03-31");  
        
        Sighting sighting2 = new Sighting();
        sighting2.setHeroId(hero2.getId());
        sighting2.setLocation(location2);
        sighting2.setDate(date2);
        sighting2 = sightingDao.addSighting(sighting2);
        
        sightings2.add(sighting2);
        
        hero2.setSightings(sightings2);
        
        heroDao.updateHero(hero2);
        
        List<Hero> heros2 = new ArrayList<>();        
        heros2.add(hero2);
        
        Organization organization2 = new Organization();
        organization2.setName("Test name2");
        organization2.setIsHero(false);
        organization2.setDescription("Test description2");
        organization2.setAddress("Test address2");
        organization2.setContact("Test contact2");
        organization2.setMembers(heros2);
        organization2 = organizationDao.addOrganization(organization2);
        
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
    }

    /**
     * Test of addOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddOrganization() {
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        
        Superpower superpower = new Superpower();
        superpower.setName("Test name");
        superpower.setDescription("Test description");
        superpower = superpowerDao.addSuperpower(superpower);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        
        List<Sighting> sightings = new ArrayList<>();        
        
        Hero hero = new Hero();
        hero.setIsHero(true);
        hero.setName("Test name");
        hero.setDescription("Test description");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Test name");
        location.setLatitude(12.3);
        location.setLongitude(-5.36);
        location.setDescription("Test description");
        location.setAddressInformation("Test address info");
        location = locationDao.addLocation(location);
        
        Date date = Date.valueOf("2018-03-31");  
        
        Sighting sighting = new Sighting();
        sighting.setHeroId(hero.getId());
        sighting.setLocation(location);
        sighting.setDate(date);
        sighting = sightingDao.addSighting(sighting);
        
        hero.setSightings(sightings);
        
        heroDao.updateHero(hero);
        
        List<Hero> heros = new ArrayList<>();        
        heros.add(hero);
        
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setIsHero(true);
        organization.setDescription("Test description");
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization.setMembers(heros);
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);

        organization.setName("Test name2");
        organization.setIsHero(false);
        organization.setDescription("Test description2");
        organization.setAddress("Test address2");
        organization.setContact("Test contact2");
        
        organizationDao.updateOrganization(organization);
        assertNotEquals(organization,fromDao);
        
        fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
    }

    /**
     * Test of getOrganizationsForHero method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetOrganizationsForHero() {
    }
    
}

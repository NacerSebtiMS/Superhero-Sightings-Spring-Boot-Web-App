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
        hero.setDescription("Test description");
        hero.setSuperpowers(superpowers);
        hero.setSightings(sightings);
        hero = heroDao.addHero(hero);
        
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
        System.out.println("Created org:\n"+organization.toString());
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        System.out.println("DAO org:\n"+fromDao.toString());
        assertEquals(organization,fromDao);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
public class SuperpowerDaoDBTest {
    
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
    
    public SuperpowerDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testGetSuperpowerById() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDaoDB.
     */
    @Test
    public void testGetAllSuperpowers() {
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testAddSuperpower() {
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testUpdateSuperpower() {
    }

    /**
     * Test of deleteSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteSuperpowerById() {
    }
    
}

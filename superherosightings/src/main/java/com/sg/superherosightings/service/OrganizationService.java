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
import com.sg.superherosightings.models.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class OrganizationService {
    
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
    
    
    
    // EXTERN DAO FUNCTIONS  

    // LOCAL DAO FUNCTIONS  
    public Organization getOrganizationById(int id){
        return organizationDao.getOrganizationById(id);      
    }
    List<Organization> getAllOrganizations(){
        return organizationDao.getAllOrganizations();
    }
    Organization addHero(Organization organization){
        return organizationDao.addOrganization(organization);
    }
    void updateOrganization(Organization hero){
        organizationDao.updateOrganization(hero);
    }
    void deleteOrganizationById(int id){
        organizationDao.deleteOrganizationById(id);
    }
}
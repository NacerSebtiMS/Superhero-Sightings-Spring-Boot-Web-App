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
    public Organization createOrganization(String name, boolean isHero, String description, String address, String contact, List<Hero> heros){
        Organization organization = new Organization();
        organization.setName(name);
        organization.setIsHero(isHero);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setContact(contact);
        organization.setMembers(heros);

        return organization;
    }
    
    
    // EXTERNAL DAO FUNCTIONS  

    // LOCAL DAO FUNCTIONS  
    public Organization getOrganizationById(int id){
        return organizationDao.getOrganizationById(id);      
    }
    public List<Organization> getAllOrganizations(){
        return organizationDao.getAllOrganizations();
    }
    public Organization addOrganization(Organization organization){
        return organizationDao.addOrganization(organization);
    }
    public void updateOrganization(Organization organization){
        organizationDao.updateOrganization(organization);
    }
    public void deleteOrganizationById(int id){
        organizationDao.deleteOrganizationById(id);
    }
}

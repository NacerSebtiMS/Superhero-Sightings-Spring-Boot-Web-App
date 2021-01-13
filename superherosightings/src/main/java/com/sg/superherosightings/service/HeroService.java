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
import com.sg.superherosightings.models.Organization;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nacer
 */
@Service
public class HeroService {
    
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
    
    private final String IMAGE_DIRECTORY = "src/main/resources/static/images";
    private final String IMAGE_EXTENSION = ".jpg";
    
    // SERVICE FUNCTION
    public Hero createHero(String name, boolean isHero, String descritpion, List<Superpower> superpowers){
        Hero hero = new Hero();
        hero.setName(name);
        hero.setIsHero(isHero);
        hero.setDescription(descritpion);
        hero.setSuperpowers(superpowers);
        hero.setSightings(new ArrayList<Sighting>());

        return hero;
    }
    
    public void uploadFile(String fileName, MultipartFile multipartFile) throws IOException{
        Path uploadPath = Paths.get(IMAGE_DIRECTORY);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName+IMAGE_EXTENSION);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        } 
    }
    
    public boolean isImageSet(String fileName){
        try{
            File f = new File(IMAGE_DIRECTORY+"/"+fileName+IMAGE_EXTENSION);
            if(f.exists() && !f.isDirectory()) { 
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            return false;
        }
        
    }
    
    
    // EXTERNAL DAO FUNCTIONS  
    public List<Organization> getOrganizationsForHero(Hero hero){
        return organizationDao.getOrganizationsForHero(hero);
    }
    public List<Location> getLocationsForHero(Hero hero){
        return locationDao.getLocationsForHero(hero);
    }
    // LOCAL DAO FUNCTIONS  
    public Hero getHeroById(int id){
        return heroDao.getHeroById(id);      
    }
    public List<Hero> getAllHeros(){
        return heroDao.getAllHeros();
    }
    public Hero addHero(Hero hero){
        return heroDao.addHero(hero);
    }
    public void updateHero(Hero hero){
        heroDao.updateHero(hero);
    }
    public void deleteHeroById(int id){
        heroDao.deleteHeroById(id);
    }
    
}

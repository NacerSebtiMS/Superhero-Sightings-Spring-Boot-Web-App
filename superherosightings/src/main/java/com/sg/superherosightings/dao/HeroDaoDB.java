/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.SightingDaoDB.SightingMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Sighting;
import com.sg.superherosightings.models.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nacer
 */
@Repository
public class HeroDaoDB implements HeroDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    SightingDaoDB sightingDaoDB;

    @Override
    public Hero getHeroById(int id) {
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM Hero WHERE HeroId = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), id);
            hero.setSuperpowers(getSuperpowersForHero(id));
            hero.setSightings(getSightingsForHero(id));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private List<Superpower> getSuperpowersForHero(int id) {
        final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s "
                + "JOIN HeroSuperpower hs ON hs.SuperpowerId = s.SuperpowerId WHERE hs.HeroId = ?";
        return jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerMapper(), id);
    }
    
    private List<Sighting> getSightingsForHero(int id) {
        final String SELECT_SIGHTINGS_FOR_HERO = "SELECT * FROM Sighting WHERE HeroId = ?";
        List<Sighting> sighthings = jdbc.query(SELECT_SIGHTINGS_FOR_HERO, new SightingMapper(), id);
        sightingDaoDB.associateLocationsForSightings(sighthings);
        return sighthings;
    }
    /*
    private void associateLocationsForSightings(List<Sighting> sighthings){
        for (Sighting sighthing : sighthings) {
            sighthing.setLocation(new SightingDaoDB().getLocationForSighting(sighthing.getId()));
        }
    }
    */

    @Override
    public List<Hero> getAllHeros() {
        final String SELECT_ALL_HEROS = "SELECT * FROM Hero";
        List<Hero> heros = jdbc.query(SELECT_ALL_HEROS, new HeroMapper());
        associateSuperpowersAndSightings(heros);
        return heros;
    }
    
    public void associateSuperpowersAndSightings(List<Hero> heros) {
        for (Hero hero : heros) {
            hero.setSuperpowers(getSuperpowersForHero(hero.getId()));
            hero.setSightings(getSightingsForHero(hero.getId()));
        }
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(IsHero, Description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_HERO,
                hero.isIsHero(),
                hero.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        insertHeroSuperpower(hero);
        insertSighting(hero);
        return hero;
    }
    
    private void insertHeroSuperpower(Hero hero){
        final String INSERT_HERO_SUPERPOWER = "INSERT INTO "
                + "HeroSuperpower(HeroId, SuperpowerId) VALUES(?,?)";
        for(Superpower superpower : hero.getSuperpowers()) {
            jdbc.update(INSERT_HERO_SUPERPOWER, 
                    hero.getId(),
                    superpower.getId());
        }
    }
    
    private void insertSighting(Hero hero){
        final String INSERT_SIGHTING = "INSERT INTO "
                + "Sighting(HeroId, LocationId, Date) VALUES(?,?,?)";
        for(Sighting sighting : hero.getSightings()){
            jdbc.update(INSERT_SIGHTING,
                    hero.getId(),
                    sighting.getLocation().getId(),
                    sighting.getDate());
            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            sighting.setId(newId);
        }
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE Hero SET IsHero = ?, Description = ?"
                + "WHERE HeroId = ?";
        jdbc.update(UPDATE_HERO,
                hero.isIsHero(),
                hero.getDescription(),
                hero.getId());
        
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, hero.getId());
        insertHeroSuperpower(hero);
        
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE HeroId = ?";
        jdbc.update(DELETE_SIGHTING, hero.getId());
        insertSighting(hero);
    }

    @Override
    @Transactional
    public void deleteHeroById(int id) {
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, id);
        
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        
        final String DELETE_HEROSIGHTING = "DELETE FROM Sighting WHERE HeroId = ?";
        jdbc.update(DELETE_HEROSIGHTING, id);
        
        final String DELETE_HERO = "DELETE FROM Hero WHERE HeroId = ?";
        jdbc.update(DELETE_HERO, id);
    }

    @Override
    public List<Hero> getHerosForSuperpower(Superpower superpower) {
        final String SELECT_HEROS_FOR_SUPERPOWER = "SELECT h.* FROM Hero h JOIN "
                + "HeroSuperpower hs ON hs.HeroId = h.HeroId WHERE hs.SuperpowerId = ?";
        List<Hero> heros = jdbc.query(SELECT_HEROS_FOR_SUPERPOWER, 
                new HeroMapper(), superpower.getId());
        associateSuperpowersAndSightings(heros);
        return heros;
    }

    @Override
    public Hero getHeroForSighting(Sighting sighting) {
        final String SELECT_HEROS_FOR_SIGHTING = "SELECT h.* FROM Hero h JOIN "
                + "Sighting s ON s.HeroId = h.HeroId WHERE s.SightingId = ?";
        Hero hero = jdbc.queryForObject(SELECT_HEROS_FOR_SIGHTING, 
                new HeroMapper(), sighting.getId());
        hero.setSuperpowers(getSuperpowersForHero(hero.getId()));
        hero.setSightings(getSightingsForHero(hero.getId()));
        return hero;
    }
    
    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("HeroId"));
            hero.setIsHero(rs.getBoolean("IsHero"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }
    }
    
}

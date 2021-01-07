/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author nacer
 */
public class Hero {
    
    private int id;
    private boolean isHero;
    private String Description;
    private List<Superpower> superpowers;
    private List<Sighting> sightings;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + (this.isHero ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.Description);
        hash = 79 * hash + Objects.hashCode(this.superpowers);
        hash = 79 * hash + Objects.hashCode(this.sightings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isHero != other.isHero) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.superpowers, other.superpowers)) {
            return false;
        }
        if (!Objects.equals(this.sightings, other.sightings)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsHero() {
        return isHero;
    }

    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }
    
}

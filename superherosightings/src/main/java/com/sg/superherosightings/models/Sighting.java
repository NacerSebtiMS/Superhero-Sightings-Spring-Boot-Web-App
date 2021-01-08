/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.models;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author nacer
 */
public class Sighting {
    
    private int id;
    private int heroId;
    private Location location;
    private Date date;

    @Override
    public String toString() {
        return "Sighting{" + "id=" + id + ", heroId=" + heroId + ", location=" + location + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.heroId;
        hash = 97 * hash + Objects.hashCode(this.location);
        hash = 97 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            System.out.println("NULL");
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("NOT SAME CLASS");
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.id != other.id) {
            System.out.println("NOT SAME ID");
            return false;
        }
        if (this.heroId != other.heroId) {
            System.out.println("NOT SAME HERO ID");
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            System.out.println("NOT SAME LOCATION");
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            System.out.println("NOT SAME DATE");
            return false;
        }
        return true;
    }



    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}

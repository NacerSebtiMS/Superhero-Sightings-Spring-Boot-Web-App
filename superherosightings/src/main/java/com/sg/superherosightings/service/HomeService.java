/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.models.Sighting;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;

/**
 *
 * @author nacer
 */
@Service
public class HomeService {
    
    public String buildUrl(List<Sighting> sightings){
        final String BASE_URL = "https://maps.googleapis.com/maps/api/staticmap?";
        // center=Brooklyn+Bridge,New+York,NY
        // &zoom=13
        // &size=600x300
        int height = 600;
        int length = 300;
        final String SIZE = "size=" + height + "x" + length;
        
        // &maptype=roadmap
        String mapType = "roadmap";
        final String MAP_TYPE = "maptype="+mapType;
        
        // &markers=color:blue%7Clabel:S%7C40.702147,-74.015794
        // &markers=color:green%7Clabel:G%7C40.711614,-74.012318
        // &markers=color:red%7Clabel:C%7C40.718217,-73.998284
        String markers = "";
        if(sightings != null){
            for(Sighting sighting : sightings){
                markers += "&markers=" 
                        + sighting.getLocation().getLatitude()
                        + ","
                        + sighting.getLocation().getLongitude();
            }
        }
        
        final String KEY = "key=" + getApiKey();
        
        final String url = BASE_URL 
                + SIZE
                + "&" + MAP_TYPE 
                + markers // & is already included
                + "&" + KEY;
        
        return url;
    }
    
    private String getApiKey(){
        final String API_KEY_FILE_PATH = "../API_KEY.txt";
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(API_KEY_FILE_PATH)));
            return sc.nextLine();
        } catch(Exception e){
            System.out.println("Error:"+e);
            return "";
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Country;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author breno
 */
public class CountryDAOTest {
    
    public CountryDAOTest() {
    }

    @Test
    public void listar() {
        
        CountryDAO dao = new CountryDAO();
        
        for(Country t: dao.select1(50)) {
            
            System.out.println(
                    "Code: "+t.getCode()+
                    "\nCity: "+t.getCity().getId()+
                    "\nName: "+t.getName()+
                    "\nContinent: "+t.getContinent()+
                    "\nRegion: "+t.getRegion()+
                    "\nSurface Area: "+t.getSurfacearea()+
                    "\nIndependence Year: "+t.getIndepyear()+
                    "\nPopulation: "+t.getPopulation()+
                    "\nLife Expectancy: "+t.getLifeexpectancy()+
                    "\nGnp: "+t.getGnp()+
                    "\nOld Gnp: "+t.getGnpold()+
                    "\nLocal Name: "+t.getLocalname()+
                    "\nGovernment Form: "+t.getGovernmentform()+
                    "\nHead of State: "+t.getHeadofstate()+
                    "\nCode 2: "+t.getCode2()+"\n"
                            
            );
            
        
    }
    
    }
    
}

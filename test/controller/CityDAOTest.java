/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.City;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author breno
 */
public class CityDAOTest {
    
    public CityDAOTest() {
    }

    @Test
    public void listar() {
        
        CityDAO dao = new CityDAO();
        
        for(City t: dao.select1(30000)) {
            
            System.out.println("Id: "+t.getId()+"\n Nome: "+t.getName()+"\n Código do País: "+t.getCountrycode()+"\n Distrito: "+t.getDistrict()+"\n Populaçao: "+t.getPopulation());
            
        
    }
    
    }
    
}

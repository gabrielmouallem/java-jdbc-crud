/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Countrylanguage;
import model.CountrylanguageId;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author breno
 */
public class CountryLanguageDAOTest {
    
    public CountryLanguageDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {
        
        Countrylanguage cl = new Countrylanguage();
        cl.setIsofficial(true);
        cl.setPercentage(70);
        
        
        CountrylanguageId clid = new CountrylanguageId();
        clid.setCountrycode("BRA");
        clid.setLanguage("Lingua do P");
        cl.setId(clid);
        
        CountryLanguageDAO dao = new CountryLanguageDAO();
        
        if(dao.save(cl)) {
            System.out.println("Salvo com sucesso");
        } else {
            fail("Erro ao salvar");
        }
        
    }
    
    @Test
    @Ignore
    public void listar() {
        
        CountryLanguageDAO dao = new CountryLanguageDAO();
        
        for(Countrylanguage t: dao.findAll()) {
            
            System.out.println("Country Code: "+t.getId().getCountrycode()+"\n Language: "+t.getId().getLanguage()+"\n É Oficial: "+t.isIsofficial()+"\n Porcentagem: "+t.getPercentage());
            
        
    }
    
    }
    
    @Test
    @Ignore
    public void alterar() {
        
        CountryLanguageDAO dao = new CountryLanguageDAO();
        
        Countrylanguage cl = new Countrylanguage();
        cl.setPercentage(80);
        
        
        CountrylanguageId clid = new CountrylanguageId();
        clid.setCountrycode("BRA");
        clid.setLanguage("Lingua do P");
        cl.setId(clid);
        
        if(dao.update(cl)) {
            System.out.println("Atualizado com sucesso");
        } else {
            fail("Erro ao salvar");
        }
    }
    
    @Test
    public void deletar() {
        
        CountryLanguageDAO dao = new CountryLanguageDAO();
        
        Countrylanguage cl = new Countrylanguage();
      
        
    
        CountrylanguageId clid = new CountrylanguageId();
        clid.setCountrycode("BRA");
        clid.setLanguage("Lingua do P");
        cl.setId(clid);
        
        if(dao.delete(cl)) {
            System.out.println("Deletado com sucesso!");
        } else {
            fail("Erro ao deletar");
        }
        
    }
    
}

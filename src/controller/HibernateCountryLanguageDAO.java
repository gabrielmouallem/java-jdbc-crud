/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Gabriel Mouallem
 * @author Gabriel Zanon
 * @author Breno Nesto
 */
import model.CountrylanguageId;
import model.Countrylanguage;
import controller.HibernateDAO;
import org.hibernate.Transaction;

public class HibernateCountryLanguageDAO {
    Countrylanguage CountryLanguage;
    CountrylanguageId id;
    Transaction transaction;
    
    public void addCountryLanguage (Countrylanguage CountryLanguage){
        HibernateDAO dao = new HibernateDAO();
        try {
            dao.save(CountryLanguage);
            startTransaction();
            commitTransaction();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        dao.closeSession();
    }
    
    public void deleteCountryLanguage (Countrylanguage CountryLanguage, CountrylanguageId id){
        HibernateDAO dao = new HibernateDAO();
        try {
            dao.load(CountryLanguage, id);
            dao.delete(CountryLanguage);
            startTransaction();
            commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
        }
        dao.closeSession();
    }
    
 
    public void updateCountryLanguage (Countrylanguage CountryLanguage, CountrylanguageId id){
        
    }
    

    public void startTransaction(){
        HibernateDAO dao = new HibernateDAO();
        transaction = dao.getSession().beginTransaction();
    }
    

    public void commitTransaction (){
        transaction.commit();
    }
}


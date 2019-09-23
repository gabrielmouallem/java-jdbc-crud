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
            startTransaction();
            dao.save(CountryLanguage);
            commitTransaction();
        }
        catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            dao.closeSession();
        }
    }
    
    public void deleteCountryLanguage (Countrylanguage CountryLanguage, CountrylanguageId id){
        HibernateDAO dao = new HibernateDAO();
        try {
            startTransaction();
            dao.load(CountryLanguage, id);
            dao.delete(CountryLanguage);
            commitTransaction();
        } 
        catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            dao.closeSession();
        }
    }
    
    // Think the update can be improvements in the future, its basic 
    public void updateCountryLanguage (Countrylanguage CountryLanguage, CountrylanguageId id, Countrylanguage newLanguage){
        HibernateDAO dao = new HibernateDAO();
        try {
            startTransaction();
            dao.load(CountryLanguage, id);
            if(newLanguage.getCountry() != null)
            CountryLanguage.setCountry(newLanguage.getCountry()); 
            if(newLanguage.getId() != null)
            CountryLanguage.setId(newLanguage.getId());
            if(newLanguage.getPercentage() != 0.0)
            CountryLanguage.setPercentage(newLanguage.getPercentage());
            dao.update(CountryLanguage);
            commitTransaction();
        } 
        catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            dao.closeSession();
        }
    }
    

    public void startTransaction(){
        HibernateDAO dao = new HibernateDAO();
        transaction = dao.getSession().beginTransaction();
    }
    

    public void commitTransaction (){
        transaction.commit();
    }
}


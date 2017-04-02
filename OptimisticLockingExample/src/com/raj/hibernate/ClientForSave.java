package com.raj.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientForSave {
/*
 * We can run any logic (Save or Update) :-) for the first time, but make sure the versioning column is a number (>=0), 
 * but save logic has ability to insert zero by default if there is no value, and update logic will directly tries to increment
 *  already existing value by 1, it wont insert any value by default if its null.	
 */
	public static void main(String[] args)
    {
 
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml"); 
 
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Product p=new Product(); 
 
        p.setProductId(104);
        p.setProName("AC");
        p.setPrice(14000);
 
        Transaction tx = session.beginTransaction();
        session.save(p);
        
        System.out.println("Object saved successfully.....!!");
        
        tx.commit();
        session.close();
        factory.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.DAO;

import br.com.loja.connection.HibernateUtil;
import br.com.loja.model.ClientModel;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author maico
 */
public class ClientDAO {

    public Long SaveClient(ClientModel client) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Long idClient = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idClient = (Long) session.save(client);
            transaction.commit();
        } catch (HibernateError e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return idClient;
    }

    public ClientModel searchClientByName(String name) {
        System.out.println("entrou busca");
        /*
        
         */
        ClientModel client = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from client where NAME = name";
            System.out.println("passou hql");
            Query query = session.createQuery(hql);
            query.setString("name",name);
            System.out.println("query");
            query.setMaxResults(1);
            client = (ClientModel) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.out.println("saiu busca");
        return client;
    }

}

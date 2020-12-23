/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.DAO;

import br.com.loja.connection.HibernateUtil;
import br.com.loja.model.AlertPaneModel;
import br.com.loja.model.ProviderModel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author maico
 */
public class ProviderDAO {

    public Long SaveProvider(ProviderModel Provider) {
        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Long idProvider = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            idProvider = (Long) session.save(Provider);
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Salvar", "O seguinte erro ocorreu ao tentar salvar o fornecedor", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return idProvider;
    }

    public void updateProvider(ProviderModel ProviderModel) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(ProviderModel);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Atualizar", "O seguinte erro ocorreu ao tentar atualizar o fornecedor", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean removeProvider(ProviderModel ProviderModel) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(ProviderModel);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Deletar", "O seguinte erro ocorreu ao tentar deletar o fornecedor", e.toString());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public ProviderModel searchById(Long idProvider) {
        ProviderModel ProviderModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ProviderModel = (ProviderModel) session.get(ProviderModel.class, idProvider);
            transaction.commit();
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o fornecedor", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return ProviderModel;
    }

    public ProviderModel searchByName(String nameProvider) {
        ProviderModel ProviderModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ProviderModel.class);
            criteria.add(Restrictions.eq("name", nameProvider));
            ProviderModel = (ProviderModel) criteria.uniqueResult();;
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o fornecedor", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return ProviderModel;
    }

    public List<ProviderModel> searchByNameList(String nameProvider) {
        List<ProviderModel> listProvider = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ProviderModel.class);
            criteria.add(Restrictions.eq("name", nameProvider));
            listProvider = criteria.list();
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o fornecedor", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listProvider;
    }

    public List<ProviderModel> searchAllProviders() {
        List<ProviderModel> ProviderList = new ArrayList<>();
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ProviderModel> criteriaQuery = criteriaBuilder.createQuery(ProviderModel.class);
            criteriaQuery.from(ProviderModel.class);
            ProviderList = session.createQuery(criteriaQuery).getResultList();

        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o fornecedor", e.toString());
        } finally {
            session.close();
        }
        return ProviderList;
    }
}

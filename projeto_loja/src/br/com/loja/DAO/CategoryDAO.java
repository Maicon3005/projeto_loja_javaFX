/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.DAO;

import br.com.loja.connection.HibernateUtil;
import br.com.loja.model.AlertPaneModel;
import br.com.loja.model.CategoryModel;
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
public class CategoryDAO {

    public Long SaveCategory(CategoryModel category) {
        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Long idCategory = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            idCategory = (Long) session.save(category);
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Salvar", "O seguinte erro ocorreu ao tentar salvar o categoria", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return idCategory;
    }
    
    public void updateCategory(CategoryModel categoryModel) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(categoryModel);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Atualizar", "O seguinte erro ocorreu ao tentar atualizar a categoria", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean removeCategory(CategoryModel categoryModel) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(categoryModel);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Deletar", "O seguinte erro ocorreu ao tentar deletar a categoria", e.toString());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public CategoryModel searchById(Long idCategory) {
        CategoryModel categoryModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            categoryModel = (CategoryModel) session.get(CategoryModel.class, idCategory);
            transaction.commit();
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar a categoria", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return categoryModel;
    }

    public CategoryModel searchByName(String nameCategory) {
        CategoryModel categoryModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(CategoryModel.class);
            criteria.add(Restrictions.eq("name", nameCategory));
            categoryModel = (CategoryModel) criteria.uniqueResult();;
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar a categoria", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return categoryModel;
    }

    public List<CategoryModel> searchByNameList(String nameCategory) {
        List<CategoryModel> listCategory = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(CategoryModel.class);
            criteria.add(Restrictions.eq("name", nameCategory));
            listCategory = criteria.list();
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar a categoria", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listCategory;
    }

    public List<CategoryModel> searchAllCategorys() {
        List<CategoryModel> categoryList = new ArrayList<>();
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CategoryModel> criteriaQuery = criteriaBuilder.createQuery(CategoryModel.class);
            criteriaQuery.from(CategoryModel.class);
            categoryList = session.createQuery(criteriaQuery).getResultList();

        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar a categoria", e.toString());
        } finally {
            session.close();
        }
        return categoryList;
    }
}

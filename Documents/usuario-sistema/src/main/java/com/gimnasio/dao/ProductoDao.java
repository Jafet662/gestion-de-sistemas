package com.gimnasio.dao;

import com.gimnasio.model.Producto;
import com.gimnasio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductoDao {
    public void save(Producto p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(p);
        tx.commit();
        s.close();
    }
    public void update(Producto p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(p);
        tx.commit();
        s.close();
    }
    public Producto findByCodigo(String codigo) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Producto> q = s.createQuery("from Producto where codigoProducto = :c", Producto.class);
        q.setParameter("c", codigo);
        Producto p = q.uniqueResult();
        s.close();
        return p;
    }
    public Producto findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Producto p = s.get(Producto.class, id);
        s.close();
        return p;
    }
    public List<Producto> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Producto> list = s.createQuery("from Producto", Producto.class).list();
        s.close();
        return list;
    }
}


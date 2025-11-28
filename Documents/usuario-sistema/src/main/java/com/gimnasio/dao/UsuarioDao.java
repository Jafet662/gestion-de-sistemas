package com.gimnasio.dao;

import com.gimnasio.model.Usuario;
import com.gimnasio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UsuarioDao {
    public void save(Usuario u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(u);
        tx.commit();
        s.close();
    }
    public void update(Usuario u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.update(u);
        tx.commit();
        s.close();
    }
    public void delete(Usuario u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.delete(u);
        tx.commit();
        s.close();
    }
    public Usuario findByCorreo(String correo) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Usuario> q = s.createQuery("from Usuario where correo = :c", Usuario.class);
        q.setParameter("c", correo);
        Usuario u = q.uniqueResult();
        s.close();
        return u;
    }
    public Usuario findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Usuario u = s.get(Usuario.class, id);
        s.close();
        return u;
    }
    public List<Usuario> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> list = s.createQuery("from Usuario", Usuario.class).list();
        s.close();
        return list;
    }
}


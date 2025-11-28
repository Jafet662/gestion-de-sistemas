package com.gimnasio.dao;

import com.gimnasio.model.Compra;
import com.gimnasio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompraDao {
    public void save(Compra c) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(c);
        tx.commit();
        s.close();
    }
}

